package com.wen.mapper;

import com.wen.pojo.Marketing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 10:39
 */
@Mapper
public interface MarketingMapper {
    List<Marketing> getMarketingList(Marketing marketing);

    List<Marketing> selectByMarketingName(String marketing_name, String marketing_people);

    Integer addMarketing(Marketing marketing);

    Marketing getMarketingByIdAccurate(String marketing_id);

    Integer updateMarketing(Marketing marketing);

    Integer deleteMarketing(String marketing_id);
}
