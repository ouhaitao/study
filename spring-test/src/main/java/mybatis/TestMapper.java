package mybatis;

import org.apache.ibatis.annotations.Param;

public interface TestMapper {
    
    String test(@Param("param") String param);
    
}
