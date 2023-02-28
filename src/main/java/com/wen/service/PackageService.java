package com.wen.service;

import com.wen.pojo.ProPackage;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/21 15:46
 */
public interface PackageService {
    List<ProPackage> getPackageList(ProPackage proPackage);

    List<ProPackage> selectByProPackageName(String package_name, String package_status);

    Integer addPackage(ProPackage proPackage);

    ProPackage getPackageByIdAccurate(int package_id);

    Integer updatePackage(ProPackage proPackage);

    Integer deletePackage(int package_id);
}
