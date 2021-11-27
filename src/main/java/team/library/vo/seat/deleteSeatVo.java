package team.library.vo.seat;

import lombok.Data;

@Data
public class deleteSeatVo {
    private Integer floor;

    private Integer row;

    private Integer column;

    private Integer userId;
}
