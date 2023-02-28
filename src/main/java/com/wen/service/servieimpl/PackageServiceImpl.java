package com.wen.service.servieimpl;

import com.wen.mapper.PackageMapper;
import com.wen.pojo.ProPackage;
import com.wen.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/21 15:47
 */
@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    private PackageMapper packageMapper;

    @Override
    public List<ProPackage> getPackageList(ProPackage proPackage) {
        return packageMapper.getPackageList(proPackage);
    }

    @Override
    public List<ProPackage> selectByProPackageName(String package_name, String package_status) {
        return packageMapper.selectByProPackageName(package_name,package_status);
    }

    @Override
    public Integer addPackage(ProPackage proPackage) {
        return packageMapper.addPackage(proPackage);
    }

    @Override
    public ProPackage getPackageByIdAccurate(int package_id) {
        return packageMapper.getPackageByIdAccurate(package_id);
    }

    @Override
    public Integer updatePackage(ProPackage proPackage) {
        return packageMapper.updatePackage(proPackage);
    }

    @Override
    public Integer deletePackage(int package_id) {
        return packageMapper.deletePackage(package_id);
    }

}
