package com.wen.mapper;

import com.wen.pojo.Voucher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 16:09
 */
@Mapper
public interface VoucherMapper {

    List<Voucher> getVoucherList(Voucher voucher);

    List<Voucher> selectByVoucherName(String voucher_name, String voucher_scene);

    Integer addVoucher(Voucher voucher);

    Voucher getVoucherByIdAccurate(String voucher_id);

    Integer updateVoucher(Voucher voucher);

    Integer deleteVoucher(String voucher_id);
}
