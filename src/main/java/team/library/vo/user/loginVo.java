package team.library.vo.user;

import lombok.Data;

/**
 * 用户表单登陆
 */
@Data
public class loginVo {
    private String username;
    private String password;
    private String code;
}
