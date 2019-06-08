package cn.wolfcode.crm.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * Created by Albert on 2019/6/4.
 */
public class CRMFreeMarkerConfigure extends FreeMarkerConfigurer{
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet(); //如果没有该代码,freemarker原本的标签就不能使用
        Configuration configuration = this.getConfiguration();
        configuration.setSharedVariable("shiro",new ShiroTags());
    }
}
