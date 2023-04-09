# 员工管理接口

## 1列出所有员工

- 说明


在数据库中列出所有员工信息

- 请求URL


http://42.192.47.5:9888/[Employee_Management](http://localhost:8080/swagger-ui.html#/operations/员工管理/listAllEmployeeUsingGET)

- HTTP请求方式

  GET

- 请求参数说明：

无需请求参数，键入URL即可



- 返回参数说明：

| 返回字段          | 字段类型 | 类型说明                                 |
| ----------------- | -------- | ---------------------------------------- |
| Employee_id       | String   | 用于唯一标识员工，使用员工 ID 来关联门店 |
| Employee_name     | String   | 在页面上显示员工姓名                     |
| Employee_mail     | String   | 员工邮件地址。可以作为用户登录名，唯一   |
| Employee_position | String   | 可选值：门店经理，副经理，小组长，店员   |
| Employee_shop     | String   | 员工所属门店                             |
| Employee_pwd      | String   | 员工登录密码                             |



- JSON返回示例

```json
{
    "code": 200,
    "message": "成功",
    "type": "success",
    "obj": [
        {
            "employeeId": "001",
            "employeeName": "杨小飞",
            "employeeMail": "227@qq.com",
            "employeePosition": "老板",
            "employeeShop": "西湖区",
            "employeePwd": "123456"
        },
        {
            "employeeId": "002",
            "employeeName": "陈思明",
            "employeeMail": "001@qq.com",
            "employeePosition": "总裁",
            "employeeShop": "拱墅区",
            "employeePwd": "123456"
        },
        {
            "employeeId": "003",
            "employeeName": "李汶津",
            "employeeMail": "002@qq.com",
            "employeePosition": "董事长",
            "employeeShop": "滨江区",
            "employeePwd": "123456"
        },
        {
            "employeeId": "004",
            "employeeName": "吴书雨",
            "employeeMail": "004@qq.com",
            "employeePosition": "副董事长",
            "employeeShop": "上城区",
            "employeePwd": "123456"
        },
        {
            "employeeId": "f6e45f76253b466d9d36304a658a3ed5",
            "employeeName": "lwj3",
            "employeeMail": "164.com",
            "employeePosition": "副董事长",
            "employeeShop": "西湖区",
            "employeePwd": "123456"
        }
    ]
}
```





## 2增加员工

+ 说明

  在数据库中增添一条员工信息

+ 请求URL

  http://42.192.47.5:9888/[Employee_Management/AddEmployee](http://localhost:8080/swagger-ui.html#/operations/员工管理/addEmployeeUsingPOST)

+ HTTP请求方式

  POST

+ 请求参数说明：

| 名称     | 字段类型 | 是否必填 | 类型说明                               |
| -------- | -------- | -------- | -------------------------------------- |
| name     | String   | True     | 在页面上显示员工姓名                   |
| mail     | String   | True     | 员工邮件地址。可以作为用户登录名，唯一 |
| position | String   | True     | 可选值：门店经理，副经理，小组长，店员 |
| shop     | String   | True     | 员工所属门店                           |
| pwd      | String   | True     | 员工登录密码                           |



- 返回参数说明：

| 返回字段          | 字段类型 | 类型说明                                 |
| ----------------- | -------- | ---------------------------------------- |
| Employee_id       | String   | 用于唯一标识员工，使用员工 ID 来关联门店 |
| Employee_name     | String   | 在页面上显示员工姓名                     |
| Employee_mail     | String   | 员工邮件地址。可以作为用户登录名，唯一   |
| Employee_position | String   | 可选值：门店经理，副经理，小组长，店员   |
| Employee_shop     | String   | 员工所属门店                             |
| Employee_pwd      | String   | 员工登录密码                             |



- JSON返回示例

```json
{
    "code": 200,
    "message": "成功",
    "type": "success",
    "obj": {
        "employeeId": "617779a1741042e48db3e3301a1d2ee9",
        "employeeName": "test",
        "employeeMail": "111@163.com",
        "employeePosition": "test",
        "employeeShop": "test",
        "employeePwd": "123456"
    }
}
```





## 3删除员工

+ 说明

  在数据库中删除一条员工信息

+ 请求URL

  http://42.192.47.5:9888/[Employee_Management/DeleteEmployee](http://localhost:8080/swagger-ui.html#/operations/员工管理/deleteEmployeeUsingPOST)

+ HTTP请求方式

  POST

+ 请求参数说明

  | 名称 | 字段类型 | 是否必填 | 类型说明                                 |
  | ---- | -------- | -------- | ---------------------------------------- |
  | id   | String   | True     | 用于唯一标识员工，使用员工 ID 来关联门店 |



- 返回参数说明：

| 返回字段 | 字段类型 | 类型说明                                                     |
| -------- | -------- | ------------------------------------------------------------ |
| SUCCESS  |          | 该返回值不是从数据库中提取的值，而是删除员工偏好成功的响应信息。 |



- JSON返回示例：

  ```json
  {
      "code": 200,
      "message": "成功",
      "type": "success",
      "obj": null
  }
  ```

  



## 4修改员工

+ 说明

  在数据库中修改一条员工信息

+ 请求URL

  http://42.192.47.5:9888/[Employee_Management/ModifyEmployee](http://localhost:8080/swagger-ui.html#/operations/员工管理/deleteEmployeeUsingPOST)

+ HTTP请求方式

  POST

+ 请求参数说明

  | 名称     | 字段类型 | 是否必填 | 类型说明                                 |
  | -------- | -------- | -------- | ---------------------------------------- |
  | id       | String   | True     | 用于唯一标识员工，使用员工 ID 来关联门店 |
  | name     | String   | True     | 在页面上显示员工姓名                     |
  | mail     | String   | True     | 员工邮件地址。可以作为用户登录名，唯一   |
  | position | String   | True     | 可选值：门店经理，副经理，小组长，店员   |
  | shop     | String   | True     | 员工所属门店                             |
  | pwd      | String   | True     | 员工登录密码                             |

  

  - 返回参数说明：

  | 返回字段 | 字段类型 | 类型说明                                                     |
  | -------- | -------- | ------------------------------------------------------------ |
  | SUCCESS  |          | 该返回值不是从数据库中提取的值，而是删除员工偏好成功的响应信息。 |



- JSON返回示例：

```json
{
    "code": 200,
    "message": "成功",
    "type": "success",
    "obj": null
}
```





## 5员工查询（根据id）

+ 说明

  在数据库中根据id查询员工信息

+ 请求URL

  http://42.192.47.5:9888/[Employee_Management/SearchById](http://localhost:8080/swagger-ui.html#/operations/员工管理/deleteEmployeeUsingPOST)

+ HTTP请求方式

  GET

+ 请求参数说明

  | 名称 | 字段类型 | 是否必填 | 类型说明                                 |
  | ---- | -------- | -------- | ---------------------------------------- |
  | id   | String   | True     | 用于唯一标识员工，使用员工 ID 来关联门店 |



+ 返回参数说明：

| 返回字段          | 字段类型 | 类型说明                                 |
| ----------------- | -------- | ---------------------------------------- |
| Employee_id       | String   | 用于唯一标识员工，使用员工 ID 来关联门店 |
| Employee_name     | String   | 在页面上显示员工姓名                     |
| Employee_mail     | String   | 员工邮件地址。可以作为用户登录名，唯一   |
| Employee_position | String   | 可选值：门店经理，副经理，小组长，店员   |
| Employee_shop     | String   | 员工所属门店                             |
| Employee_pwd      | String   | 员工登录密码                             |



- JSON返回示例

  ```json
  {
      "code": 200,
      "message": "成功",
      "type": "success",
      "obj": {
          "employeeId": "f6e45f76253b466d9d36304a658a3ed5",
          "employeeName": "lwj3",
          "employeeMail": "164.com",
          "employeePosition": "test",
          "employeeShop": "test",
          "employeePwd": "123456"
      }
  }
  ```





## 6员工查询（根据name）

+ 说明

  在数据库中根据name查询员工信息

+ 请求URL

  http://42.192.47.5:9888/[Employee_Management/SearchByName](http://localhost:8080/swagger-ui.html#/operations/员工管理/deleteEmployeeUsingPOST)

+ HTTP请求方式

  POST

+ 请求参数说明

  | 名称 | 字段类型 | 是否必填 | 类型说明             |
  | ---- | -------- | -------- | -------------------- |
  | name | String   | True     | 在页面上显示员工姓名 |



+ 返回参数说明：

| 返回字段          | 字段类型 | 类型说明                                 |
| ----------------- | -------- | ---------------------------------------- |
| Employee_id       | String   | 用于唯一标识员工，使用员工 ID 来关联门店 |
| Employee_name     | String   | 在页面上显示员工姓名                     |
| Employee_mail     | String   | 员工邮件地址。可以作为用户登录名，唯一   |
| Employee_position | String   | 可选值：门店经理，副经理，小组长，店员   |
| Employee_shop     | String   | 员工所属门店                             |
| Employee_pwd      | String   | 员工登录密码                             |



- JSON返回示例：

```json
{
    "code": 200,
    "message": "成功",
    "type": "success",
    "obj": [
        {
            "employeeId": "f6e45f76253b466d9d36304a658a3ed5",
            "employeeName": "lwj3",
            "employeeMail": "164.com",
            "employeePosition": "test",
            "employeeShop": "test",
            "employeePwd": "123456"
        }
    ]
}
```

