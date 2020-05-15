package ouht.factory;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class GenericFactory {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private RegistryConfig registryConfig;

    public Object $Invoke(String interfaceName, String methodName, String[] paramterType, Object[] args){
        ReferenceConfig<GenericService> referenceConfig=new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(interfaceName);
        referenceConfig.setGeneric(true);
        ReferenceConfigCache cache=ReferenceConfigCache.getCache();
        GenericService genericService=cache.get(referenceConfig);

        return genericService.$invoke(methodName,paramterType,args);
    }


    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR,-777);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));

    }
}
