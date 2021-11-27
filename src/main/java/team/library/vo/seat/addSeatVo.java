package team.library.vo.seat;


import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class addSeatVo {

    private Integer floor;

    private Integer row;

    private Integer column;

    private Integer userId;

    private Integer has;
}
