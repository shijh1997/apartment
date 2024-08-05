package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.web.admin.mapper.*;
import com.atguigu.lease.web.admin.service.LeaseAgreementService;
import com.atguigu.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.atguigu.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement> implements LeaseAgreementService {
    @Autowired
    private LeaseAgreementMapper mapper;

    @Autowired
    private ApartmentInfoMapper infoMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Override
    public IPage<AgreementVo> PageItem(Page<AgreementVo> page, AgreementQueryVo queryVo) {
        return mapper.pageItem(page, queryVo);
    }

    @Override
    public AgreementVo getAgreementById(Long id) {
        LeaseAgreement leaseAgreement = mapper.selectById(id);

        ApartmentInfo apartmentInfo = infoMapper.selectById(leaseAgreement.getApartmentId());

        RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getApartmentId());

        PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getApartmentId());

        LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getApartmentId());
        AgreementVo agreementVo = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreement, agreementVo);
        agreementVo.setLeaseTerm(leaseTerm);
        agreementVo.setRoomInfo(roomInfo);
        agreementVo.setApartmentInfo(apartmentInfo);
        agreementVo.setPaymentType(paymentType);
        return agreementVo;
    }
}




