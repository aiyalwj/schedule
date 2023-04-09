#  Intelligent shift scheduling system APIs
[toc]
## 1	环境变量

### 默认环境1
| 参数名 | 字段值 |
| ------ | ------ |
|baseUrl|http://localhost:50070|
|serverUrl|http://42.192.47.5:9047|


## 2	 Intelligent shift scheduling system APIs

##### 说明
> 



##### 联系方式
- **联系人：**
- **邮箱：**
- **网址：**//

##### 文档版本
```
1.0
```


## 3	排班规则管理

## 3.1	列出所有规则

> GET  /Rule_Manager
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 3.2	创建规则

> POST  /Rule_Manager/CreateRules
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|Rule_shop||Rule_shop|
|Rule_type||Rule_type|
|Rule_value||Rule_value|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 3.3	删除规则

> POST  /Rule_Manager/DeleteRules
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|Rule_shop||Rule_shop|
|Rule_type||Rule_type|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 3.4	修改规则值

> POST  /Rule_Manager/ModifyRules
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|Rule_shop||Rule_shop|
|Rule_type||Rule_type|
|Rule_value||Rule_value|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 3.5	根据规则名和店铺名查询规则

> GET  /Rule_Manager/SearchByNames
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|Rule_shop||Rule_shop|
|Rule_type||Rule_type|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 4	门店管理

## 4.1	列出所有的门店

> GET  /Shop_Management
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 4.2	增加门店

> POST  /Shop_Management/AddShop
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|address||address|
|name||name|
|size||size|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 4.3	删除门店

> POST  /Shop_Management/DeleteShop
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|id||id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 4.4	修改门店

> POST  /Shop_Management/ModifyShop
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|address||address|
|id||id|
|name||name|
|size||size|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 4.5	门店查询（根据id）

> GET  /Shop_Management/SearchById
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|id||id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 4.6	门店查询（根据name）

> POST  /Shop_Management/SearchByName
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|name||name|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 5	员工偏好管理

## 5.1	创建员工偏好

> POST  /Employeeprefer_Management/CreateEP
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|EmployeePreferences_type||EmployeePreferences_type|
|EmployeePreferences_value||EmployeePreferences_value|
|Employee_id||Employee_id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 5.2	删除员工偏好

> POST  /Employeeprefer_Management/DeleteEP
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|EmployeePreferences_type||EmployeePreferences_type|
|Employee_id||Employee_id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 5.3	修改员工偏好

> POST  /Employeeprefer_Management/ModifyEP
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|EmployeePreferences_type||EmployeePreferences_type|
|EmployeePreferences_value||EmployeePreferences_value|
|Employee_id||Employee_id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 5.4	员工偏好查询（根据偏好类型）

> GET  /Employeeprefer_Management/SearchByEPType
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|EmployeePreferences_type||EmployeePreferences_type|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 5.5	员工偏好查询（根据id）

> GET  /Employeeprefer_Management/SearchById
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|Employee_id||Employee_id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6	员工管理

## 6.1	列出所有员工

> GET  /Employee_Management
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6.2	增加员工

> POST  /Employee_Management/AddEmployee
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|mail||mail|
|name||name|
|position||position|
|pwd||pwd|
|shop||shop|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6.3	删除员工

> POST  /Employee_Management/DeleteEmployee
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|id||id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6.4	列出某店的员工

> GET  /Employee_Management/ListSameshopep
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|shop||shop|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6.5	修改员工

> POST  /Employee_Management/ModifyEmployee
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|id||id|
|mail||mail|
|name||name|
|position||position|
|pwd||pwd|
|shop||shop|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6.6	员工查询（根据id）

> GET  /Employee_Management/SearchById
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|id||id|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 6.7	员工查询（根据name）

> POST  /Employee_Management/SearchByName
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|name||name|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 




## 7	管理者登录

## 7.1	用户登录

> GET  /Verification/Login
### 请求参数(Query Param)
| 参数名称 | 默认值 | 描述 |
| ------ | ------ | ------ |
|Employee_id||Employee_id|
|Employee_pwd||Employee_pwd|
### 响应体
● 200 响应数据格式：JSON
| 参数名称 | 类型 | 默认值 | 不为空 | 描述 |
| ------ | ------ | ------ | ------ | ------ |
| code|int32||false||
| message|string||false||
| obj|object||false||
| type|string||false||

##### 接口描述
> 



