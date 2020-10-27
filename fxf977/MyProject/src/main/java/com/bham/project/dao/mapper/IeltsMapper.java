package com.bham.project.dao.mapper;

import com.bham.project.entity.Ielts;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: Fuwei Feng
 * @version: 2020/7/15
 */
public interface IeltsMapper extends Mapper<Ielts> {
    @Select("SELECT * FROM ielts WHERE aid=#{aid}")
    @Results(id = "IeltsMap", value = {@Result(property = "aid", column = "aid", id = true),
        @Result(property = "read", column = "read"),
        @Result(property = "listen", column = "listen"),
        @Result(property = "speak", column = "speak"),
        @Result(property = "write", column = "write"),
        @Result(property = "overall", column = "overall")
    })
    public Ielts selectByAid(@Param("aid") String aid);
}
