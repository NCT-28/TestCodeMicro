//package com.service.account.endpoint.impl;
//
//import com.base.dtos.http.AuthorityHTTPEndpointsService;
//import com.base.dtos.http.ExDTO;
//import com.base.dtos.http.HTTPEndpointsDTO;
//import com.original.tag.models.endpoint.TagHTTPEndpoints;
//import com.service.account.endpoint.HTTPEndpointsService;
//import com.source.base.controller.constants.Path;
//import com.source.base.service.rest.BaseImplRestService;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//public class HTTPEndpointsServiceRest extends BaseImplRestService<HTTPEndpointsDTO, UUID> implements AuthorityHTTPEndpointsService, HTTPEndpointsService<HTTPEndpointsDTO> {
//
//    public HTTPEndpointsServiceRest(RestTemplate r) {
//        super(r, HTTPEndpointsDTO.class, AccountServer.host + Path.EXCHANGE_URL + "/" + TagHTTPEndpoints.name, AccountServer.user, AccountServer.pass);
//    }
//
//    public boolean check(String p, List<?> a) {
//        return this.postForEntity("/auth", new ExDTO(p, a), Boolean.class);
//    }
//
//    @Override
//    public Boolean checkExist(HTTPEndpointsDTO dto) {
//        return this.postForEntity("/check", dto, Boolean.class);
//    }
//
//    @Override
//    public HTTPEndpointsDTO findEndpoints(HTTPEndpointsDTO dto) {
//        return this.postForEntity("/findOne", dto, HTTPEndpointsDTO.class);
//    }
//
//    @Override
//    public List<String> findAllEndpointByServerName(String serverName) {
//        List<?> e = this.postForEntity("/findAllEndpointByServerName", new HTTPEndpointsDTO(serverName), List.class);
//        if (e == null) {
//            return null;
//        }
//        return e.stream().map(Object::toString).collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean deleteWithPatternAndServerName(String pattern, String serverName) {
//        return this.postForEntity("/deleteWithPatternAndServerName", new HTTPEndpointsDTO(pattern, serverName), Boolean.class);
//    }
//}