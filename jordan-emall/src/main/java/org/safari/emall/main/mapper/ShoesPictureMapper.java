package org.safari.emall.main.mapper;

import java.util.List;
import org.safari.emall.main.entity.ShoesPicture;

public interface ShoesPictureMapper {

    public int insert(ShoesPicture shoesPicture);

    public int deleteById(String id);

    public int deleteByIds(List<String> idList);

    public ShoesPicture findById(String id);

	public List<ShoesPicture> findByShoesId(String shoesId);
}