package com.tangcheng.oauth2.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * oauth2-learning
 *
 * @author tangcheng
 * @date 7/9/2018 7:30 AM
 */
@Api(tags = "oauth/user", description = "被保护服务的鉴权API")
@RestController
public class UserController {

    @ApiOperation(value = "根据Token获取用户信息", notes = "根据Token获取用户信息")
    @GetMapping("user")
    public ResponseEntity user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return ResponseEntity.ok(userInfo);
    }

}
