package com.bham.project.dao.mapper;

import com.bham.project.entity.Ielts;
import com.bham.project.entity.Toefl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/17
 */
public interface ToeflMapper extends Mapper<Toefl> {
    @Select("SELECT * FROM toefl WHERE aid=#{aid}")
    @Results(id = "ToeflMap", value = {@Result(property = "aid", column = "aid", id = true),
            @Result(property = "read", column = "read"),
            @Result(property = "listen", column = "listen"),
            @Result(property = "speak", column = "speak"),
            @Result(property = "write", column = "write"),
            @Result(property = "overall", column = "overall")
    })
    public Ielts selectByAid(@Param("aid") String aid);
}
