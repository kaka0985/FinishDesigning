package com.wen.service.servieimpl;

import com.wen.mapper.VoucherMapper;
import com.wen.pojo.Voucher;
import com.wen.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 16:08
 */
@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherMapper voucherMapper;

    @Override
    public List<Voucher> getVoucherList(Voucher voucher) {
        return voucherMapper.getVoucherList(voucher);
    }

    @Override
    public List<Voucher> selectByVoucherName(String voucher_name, String voucher_scene) {
        return voucherMapper.selectByVoucherName(voucher_name,voucher_scene);
    }

    @Override
    public Integer addVoucher(Voucher voucher) {
        return voucherMapper.addVoucher(voucher);
    }

    @Override
    public Voucher getVoucherByIdAccurate(String voucher_id) {
        return voucherMapper.getVoucherByIdAccurate(voucher_id);
    }

    @Override
    public Integer updateVoucher(Voucher voucher) {
        return voucherMapper.updateVoucher(voucher);
    }

    @Override
    public Integer deleteVoucher(String voucher_id) {
        return voucherMapper.deleteVoucher(voucher_id);
    }
}
