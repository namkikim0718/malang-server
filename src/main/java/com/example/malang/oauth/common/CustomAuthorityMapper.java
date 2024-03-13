package com.example.malang.oauth.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.Collection;
import java.util.HashSet;

public class CustomAuthorityMapper implements GrantedAuthoritiesMapper {
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        HashSet<GrantedAuthority> mapped = new HashSet<>();

        for (GrantedAuthority authority : mapped) {
            mapped.add(mapAuthority(authority.getAuthority()));
        }

        /**
         * 어떤 소셜 로그인이든 검증된 사용자에게 MALANG_ROLE 권한을 부여합니다.
         */
        mapped.add(new SimpleGrantedAuthority("MALANG_ROLE"));
        return mapped;
    }
    private GrantedAuthority mapAuthority(String name) {
        return new SimpleGrantedAuthority(name);
    }
}
