package com.wen.mapper;

import com.wen.pojo.ProPackage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/21 15:48
 */
@Mapper
public interface PackageMapper {
    List<ProPackage> getPackageList(ProPackage proPackage);

    List<ProPackage> selectByProPackageName(String package_name, String package_status);

    Integer addPackage(ProPackage proPackage);

    ProPackage getPackageByIdAccurate(int package_id);

    Integer updatePackage(ProPackage proPackage);

    Integer deletePackage(int package_id);
}
