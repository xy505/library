package team.library.vo;

import lombok.Data;

@Data
public class pageQuery {

    private Integer page=1;
    private Integer limit=10;
}
