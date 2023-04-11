package com.lwj.schedule.service.impl;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.schedule.dto.RespBean;
import com.lwj.schedule.dto.RespBeanEnum;
import com.lwj.schedule.entity.Employee;
import com.lwj.schedule.mapper.EmployeeMapper;
import com.lwj.schedule.service.EmployeeService;
import com.lwj.schedule.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author yangxiaofei
* @description 针对表【Employee(员工)】的数据库操作Service实现
* @createDate 2023-03-01 14:35:35
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    @Transactional
    public RespBean Login(String employee_id, String employee_pwd) {
        if(employee_id == "" || employee_id == null || employee_pwd == "" || employee_pwd ==null){
            return RespBean.error(RespBeanEnum.LOGIN_INPUT_EMPTY);
        }

        Employee employee = employeeMapper.listEmployeeById(employee_id);

        //用户不存在
        if(employee == null){
            return RespBean.error(RespBeanEnum.EMP_ID_NOT_FOUND);
        }

        //加密
//        if(!MD5Utils.inputPassToDBPass(password,user.getSalt()).equals(user.getUserPwd())) {
        if(!employee_pwd.equals(employee.getEmployeePwd())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        return RespBean.success(employee);
    }

    @Override
    @Transactional
    public RespBean addEmployee(String name, String mail, String position, String shop, String pwd) {
        if(name == "" || name ==null){
            return RespBean.error(RespBeanEnum.EMP_NAME_EMPTY);
        }
        if(mail == "" || mail == null){
            return RespBean.error(RespBeanEnum.EMP_MAIL_EMPTY);
        }
        if(position == "" || position ==null){
            return RespBean.error(RespBeanEnum.EMP_POSITION_EMPTY);
        }
        if(shop == "" || shop ==null){
            return RespBean.error(RespBeanEnum.EMP_SHOP_EMPTY);
        }
        if(pwd == "" || pwd ==null){
            return RespBean.error(RespBeanEnum.EMP_PWD_EMPTY);
        }
        if(employeeMapper.listEmployeeByMail(mail) != null){
            return RespBean.error(RespBeanEnum.EMP_MAIL_EXIST);
        }
        String id = RandomUtils.generateTicket();
//        while(true){
//            if(employeeMapper.listEmployeeById(id)==null){
//                break;
//            }else{
//                id = RandomUtils.generateTicket();
//            }
//        }

        Employee employee = new Employee();
        employee.setEmployeeId(id);
        employee.setEmployeeName(name);
        employee.setEmployeeMail(mail);
        employee.setEmployeePosition(position);
        employee.setEmployeeShop(shop);
        employee.setEmployeePwd(pwd);
        employeeMapper.addEmployee(employee);
//        try{
//            employeeMapper.addEmployee(employee);
//        }catch (Exception e){
//            return RespBean.error(RespBeanEnum.EMP_MAIL_EXIST);
//        }

        return RespBean.success(employee);
    }

    @Override
    @Transactional
    public RespBean listAll() {
        List<Employee> employeeList = employeeMapper.listAllEmployee();
        return RespBean.success(employeeList);
    }


    @Override
    @Transactional
    public RespBean searchById(String id) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.EMP_ID_EMPTY);
        }
        Employee employee = employeeMapper.listEmployeeById(id);

        if(employee == null){
            return RespBean.error(RespBeanEnum.EMP_ID_NOT_FOUND);
        }

        return RespBean.success(employee);
    }

    @Override
    @Transactional
    public RespBean searchByName(String name) {
        if(name.equals("")||name == null){
            return RespBean.error(RespBeanEnum.EMP_NAME_EMPTY);
        }
        List<Employee> employeeList = employeeMapper.listEmployeeByName(name);

        if(employeeList == null){
            return RespBean.error(RespBeanEnum.EMP_NAME_NOT_FOUND);
        }

        return RespBean.success(employeeList);
    }

    @Override
    @Transactional
    public RespBean modifyById(String id,String name,String mail,String position,String shop,String pwd) {
        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.EMP_ID_EMPTY);
        }
        List<Employee> mailList = employeeMapper.listEmployeeByMail(mail);
        if(mailList.size() > 1){//改过的邮箱跟已有的冲突
            return RespBean.error(RespBeanEnum.EMP_MAIL_EXIST);
        }else if (mailList.size() == 1){//邮箱跟原来一样
            employeeMapper.modifyEmployeeByIdExceptMail(id,name,position,shop,pwd);
        }else {
            employeeMapper.modifyEmployeeById(id, name, mail, position, shop, pwd);
        }


        return RespBean.success();
    }

    @Override
    @Transactional
    public RespBean deleteById(String id) {

        if(id.equals("")||id == null){
            return RespBean.error(RespBeanEnum.EMP_ID_EMPTY);
        }

        Employee employee = employeeMapper.listEmployeeById(id);
        if(employee == null){
            return RespBean.error(RespBeanEnum.EMP_ID_NOT_FOUND);
        }

        employeeMapper.deleteEmployeeById(id);
        return RespBean.success();
    }

    @Override
    @Transactional
    public RespBean listEmployeeBySameshop(String shop) {
        List<Employee> employeesameShopList = employeeMapper.listEmployeeBySameShop(shop);
        if(employeesameShopList == null){
            return RespBean.error(RespBeanEnum.SHOP_ID_NOT_FOUND);
        }
        return RespBean.success(employeesameShopList);
    }
}




