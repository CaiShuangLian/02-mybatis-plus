package com.mp.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/8/9 17:29
 * @Version:
 * @Description:TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeList  implements Serializable {

    private Date day;

    private List<String > time;
}
