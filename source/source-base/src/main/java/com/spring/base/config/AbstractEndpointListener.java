package com.spring.base.config;

import com.base.dtos.http.IEndpoints;
import com.base.enums.HttpMethod;
import com.source.base.controller.annotations.PublicController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> Object thể hiển của Endpoints
 * @author Nguyễn Đình Tạo
 * @version 1.0
 */
public abstract class AbstractEndpointListener<T extends IEndpoints> implements ApplicationListener<ContextRefreshedEvent> {

    private final Environment env;
    private final boolean init;
    private final boolean update;

    public AbstractEndpointListener(Environment env, boolean init, boolean update) {
        this.env = env;
        this.init = init;
        this.update = update;
    }

    public abstract T initEndpoints();

    public abstract String getServerName();

    public abstract boolean checkExistEndpoints(T endpoints);

    public abstract void saveEndpoints(T endpoints);

    public abstract void saveAllEndpoints(List<T> collection);

    public abstract T findEndpoints(T endpoints);

    public abstract void updateEndpoints(T endpoints);

    public abstract List<String> findAllEndpointByServerName(String serverName);

    public abstract boolean deleteWithPatternAndServerName(String pattern, String serverName);

    /**
     * Kiểm tra có thay đổi so với endpoint gốc
     *
     * @param endpointsNew Endpoints mới
     * @param endpointsOld Endpoints cũ
     * @return true / false
     */
    private boolean hasBeenModified(T endpointsNew, T endpointsOld) {
        if (endpointsNew != null && endpointsOld != null) {
            if (endpointsNew.getIsPublic() != null) {
                if (!endpointsNew.getIsPublic().equals(endpointsOld.getIsPublic())) {
                    return true;
                }
            }
            if (endpointsNew.getMethod() != null) {
                if (!endpointsNew.getMethod().equals(endpointsOld.getMethod())) {
                    return true;
                }
            }
            if (endpointsNew.getSummary() != null) {
                if (!endpointsNew.getSummary().equals(endpointsOld.getSummary())) {
                    return true;
                }
            }
            if (endpointsNew.getGroupName() != null) {
                if (!endpointsNew.getGroupName().equals(endpointsOld.getGroupName())) {
                    return true;
                }
            }
            return !endpointsNew.getGroupDescription().equals(endpointsOld.getGroupDescription());
        }
        return false;
    }

    /**
     * Insert new Endpoints
     *
     * @param endpoints data
     */
    private void insert(T endpoints) {
        System.out.println("Insert new Endpoints: " + endpoints);
        this.saveEndpoints(endpoints);
    }

    /**
     * Kiểm tra Uri có trong danh sách hay không
     *
     * @param collection danh sách cần kiểm tra
     * @param pattern    uri cần kiểm tra
     * @return true: có tồn tại / false: không tồn tại
     */
    private boolean checkExistPattern(List<T> collection, String pattern) {
        for (T t : collection) {
            if (t.getPattern().equals(pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * convert RequestMethod
     *
     * @param requestMethod {@link RequestMethod}
     * @return HttpMethod
     */
    public HttpMethod convertRequestMethod(RequestMethod requestMethod) {
        if (requestMethod == RequestMethod.GET) {
            return HttpMethod.GET;
        }
        if (requestMethod == RequestMethod.HEAD) {
            return HttpMethod.HEAD;
        }
        if (requestMethod == RequestMethod.POST) {
            return HttpMethod.POST;
        }
        if (requestMethod == RequestMethod.PUT) {
            return HttpMethod.PUT;
        }
        if (requestMethod == RequestMethod.PATCH) {
            return HttpMethod.PATCH;
        }
        if (requestMethod == RequestMethod.DELETE) {
            return HttpMethod.DELETE;
        }
        if (requestMethod == RequestMethod.OPTIONS) {
            return HttpMethod.OPTIONS;
        }
        if (requestMethod == RequestMethod.TRACE) {
            return HttpMethod.TRACE;
        }
        return null;
    }

    /**
     * Lọc tất cả các Bean mỏ rộng từ lớp {@link RequestMappingHandlerMapping}
     *
     * @param contextRefreshedEvent event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // Nếu đang trên mồi trường production thì không cần kiểm tra
        if ("prod".equals(env.getProperty("spring.profiles.active", "prod"))) {
            return;
        }
        String appName = env.getProperty("spring.application.name");
        if (appName != null) {
            if ("Auctions_Account".equals(appName)) {
                String ddl = env.getProperty("spring.jpa.hibernate.ddl-auto");
                String generate = env.getProperty("spring.jpa.generate-ddl");
                String initialization = env.getProperty("spring.datasource.initialization-mode");
                if (ddl != null || generate != null || initialization != null) {
                    System.out.println("Whether to initialize the schema on startup. | " + generate);
                    System.out.println("Initialize the datasource with available DDL and DML scripts. | " + initialization);
                    return;
                }
            }
        }
        // Khởi tạo danh sách các endpoint có trong project. Mặc định là rỗng.
        List<T> collection = new ArrayList<>();
        // Lấy danh sách các Bean được khởi tạo
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        context
                .getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods()
                .forEach((requestMappingInfo, handlerMethod) -> {
                    T endpoints = this.initEndpoints();
                    for (String pattern : requestMappingInfo.getPatternsCondition().getPatterns()) {
                        endpoints.setPattern(pattern);
                    }
                    for (RequestMethod requestMethod : requestMappingInfo.getMethodsCondition().getMethods()) {
                        endpoints.setMethod(convertRequestMethod(requestMethod));
                    }
                    for (Annotation annotation : handlerMethod.getMethod().getDeclaredAnnotations()) {
                        if (annotation.annotationType().equals(Operation.class)) {
                            Operation operation = (Operation) annotation;
                            endpoints.setSummary(operation.summary());
                        }
                    }
                    endpoints.setIsPublic(false);
                    endpoints.setIsSecurity(false);
                    for (Annotation annotation : handlerMethod.getBeanType().getDeclaredAnnotations()) {
                        if (annotation.annotationType().equals(Tag.class)) {
                            Tag tag = (Tag) annotation;
                            endpoints.setGroupName(tag.name());
                            endpoints.setGroupDescription(tag.description());
                        }
                        if (annotation.annotationType().equals(PublicController.class)) {
                            endpoints.setIsPublic(true);
                        }
                        if (annotation.annotationType().equals(SecurityRequirement.class)) {
                            endpoints.setIsSecurity(true);
                        }
                    }
                    endpoints.setServerName(this.getServerName());
                    if (endpoints.getPattern() != null && endpoints.getMethod() != null && endpoints.getGroupName() != null) {
                        // Kiểm tra có cần cập nhật lại các endpoint
                        if (update) {
                            // Tìm endpoint đã tồn tại
                            T endpointsOld = this.findEndpoints(endpoints);
                            if (endpointsOld == null) {
                                this.insert(endpoints);
                            } else {
                                // Nếu không tìm thấy endpoint nào thì thêm mới một endpoint
                                if (endpointsOld.getUuid() == null) {
                                    this.insert(endpoints);
                                } else {
                                    // Kiểm tra endpoint có sửa đổi gì không thì cập nhật lại endpoint
                                    if (this.hasBeenModified(endpoints, endpointsOld)) {
                                        System.out.println("Update Endpoints: " + endpoints);
                                        endpoints.setUuid(endpointsOld.getUuid());
                                        this.updateEndpoints(endpoints);
                                    }
                                }
                            }
                        } else {
                            // Kiểm tra endpoint đã tồn tại hay chưa
                            if (!this.checkExistEndpoints(endpoints)) {
                                this.insert(endpoints);
                            }
                        }
                        // Thêm endpoint tới danh sách đã tìm thấy trong project
                        collection.add(endpoints);
                    }
                });
        // Kiểm tra 2 danh sách trên database và danh sách các endpoint trong source có trùng với nhau hay không
        List<String> patterns = this.findAllEndpointByServerName(getServerName());
        if (patterns != null) {
            // Xóa những endpoint trên database không tồn tại trong source
            patterns.forEach(pattern -> {
                if (!this.checkExistPattern(collection, pattern)) {
                    try {
                        boolean deleted = this.deleteWithPatternAndServerName(pattern, getServerName());
                        System.out.println("Deleted Endpoints: " + pattern + " | " + deleted);
                    } catch (Exception ex) {
                        System.out.println("Deleted Endpoints: " + pattern + " | " + false + " | error: " + ex.getMessage());
                    }
                }
            });
        }
        // TODO chỗ này đang suy nghĩ
        if (!collection.isEmpty()) {
            if (init) {
                System.out.println(collection);
                this.saveAllEndpoints(collection);
            }
        }
    }
}