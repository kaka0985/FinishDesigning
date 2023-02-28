package com.wen.service.servieimpl;

import com.wen.mapper.MarketingMapper;
import com.wen.pojo.Marketing;
import com.wen.service.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 10:40
 */
@Service
public class MarketingServiceImpl implements MarketingService {

    @Autowired
    private MarketingMapper marketingMapper;

    @Override
    public List<Marketing> getMarketingList(Marketing marketing) {
        return marketingMapper.getMarketingList(marketing);
    }

    @Override
    public List<Marketing> selectByMarketingName(String marketing_name, String marketing_people) {
        return marketingMapper.selectByMarketingName(marketing_name,marketing_people);
    }

    @Override
    public Integer addMarketing(Marketing marketing) {
        return marketingMapper.addMarketing(marketing);
    }

    @Override
    public Marketing getMarketingByIdAccurate(String marketing_id) {
        return marketingMapper.getMarketingByIdAccurate(marketing_id);
    }

    @Override
    public Integer updateMarketing(Marketing marketing) {
        return marketingMapper.updateMarketing(marketing);
    }

    @Override
    public Integer deleteMarketing(String marketing_id) {
        return marketingMapper.deleteMarketing(marketing_id);
    }
}
