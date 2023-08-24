package com.yunjin.springbootblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", updatable = false)
    private Long id;

    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @Column (name = "password", nullable = false)
    private String password;

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자의 id를 반환 (고유한 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 사용자의 패스워드를 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환 (만료되었는지 확인하는 로직)
    @Override
    public boolean isAccountNonExpired() {
        return true; // true : 만료되지 않았음
    }

    // 계정 잠금 여부 반환 (계정 잠금되었는지 확인하는 로직)
    @Override
    public boolean isAccountNonLocked() {
        return true; // true : 잠금되지 않음
    }

    // 패스워드의 만료 여부 반환 (패스워드가 만료되었는 지 확인하는 로직)
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // true : 만료되지 않음
    }

    // 계정 사용 가능 여부 반환 (계정이 사용 가능한지 확인하는 로직)
    @Override
    public boolean isEnabled() {
        return true; // true : 사용 가능
    }
}