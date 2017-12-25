package org.safari.sport.main.mapper;

import org.safari.sport.main.entity.Evaluate;

public interface EvaluateMapper {

    public int insert(Evaluate evaluate);

    public int modify(Evaluate evaluate);

    public Evaluate findById(String id);

    public Evaluate findByVipId(String vipId);
}