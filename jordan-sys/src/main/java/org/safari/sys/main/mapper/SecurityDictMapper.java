package org.safari.sys.main.mapper;

import java.util.List;
import org.safari.sys.main.entity.SecurityDict;

public interface SecurityDictMapper {

    public SecurityDict findById(String id);

    public List<SecurityDict> findAll(String deleteNo);
}