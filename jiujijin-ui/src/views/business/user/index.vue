<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in statusList"
            :key="dict.value"
            :label="dict.name"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="注册时间" prop="registerTime">
        <el-date-picker
          v-model="dateRange"
          style="width: 340px"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetimerange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="用户ID" align="center" prop="id" /> -->
      <el-table-column label="用户名/姓名" align="center" prop="bankName" width="200">
        <template slot="header">
          <div>用户名</div>
          <div>姓名</div>
          <div>邮箱</div>
        </template>
        <template slot-scope="scope">
          <div>{{scope.row.userName || "-"}}</div>
          <div>{{scope.row.realName || "-"}}</div>
          <div>{{scope.row.email || "-"}}</div>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <div v-if="scope.row.gender === 1">男</div>
          <div v-else-if="scope.row.gender === 2">女</div>
          <div v-else-if="scope.row.gender === 3">其他</div>
        </template>
      </el-table-column>
      <el-table-column align="center" width="180">
        <template slot="header">
          <div>国籍</div>
          <div>护照/身份证</div>
        </template>
        <template slot-scope="scope">
          <div>{{ scope.row.nationality }}</div>
          <div>{{ scope.row.idCard }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" width="180">
        <template slot="header">
          <div>工作</div>
          <div>生日</div>
        </template>
        <template slot-scope="scope">
          <div>{{scope.row.work || "-"}}</div>
          <div>{{scope.row.birth || "-"}}</div>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="bankName" width="300">
        <template slot-scope="scope">
          <div>省/市：{{scope.row.city || "-"}}</div>
          <div>区：{{scope.row.region || "-"}}</div>
          <div>详细地址：{{scope.row.address || "-"}}</div>
        </template>
      </el-table-column>
      <el-table-column label="银行信息" align="center" prop="bankName" width="200">
        <template slot-scope="scope">
          <div>{{scope.row.bankName}}</div>
          <div>{{scope.row.bankCard}}</div>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            @change="changeStatus(scope.row.id,scope.row.status)"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column align="center" width="180">
        <template slot="header">
          <div>注册时间</div>
          <div>上次登陆时间</div>
        </template>
        <template slot-scope="scope">
          <div>{{ scope.row.registerTime }}</div>
          <div>{{ scope.row.lastTime }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" width="180">
        <template slot="header">
          <div>注册IP</div>
          <div>上次登陆IP</div>
        </template>
        <template slot-scope="scope">
          <div>{{ scope.row.registerIp }}</div>
          <div>{{ scope.row.lastIp }}</div>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:user:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" :disabled="true" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="姓名" prop="realName">
          <el-input v-model="form.realName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="登录密码" prop="loginPwd">
          <el-input v-model="form.loginPwd" placeholder="不输入表示不修改" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option
              v-for="dict in genderList"
              :key="dict.value"
              :label="dict.name"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="国籍" prop="nationality">
          <el-input v-model="form.nationality" placeholder="请输入国籍" />
        </el-form-item>
        <el-form-item label="生日" prop="birth">
          <el-date-picker clearable
            v-model="form.birth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生日">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="身份证/护照" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入身份证/护照" />
        </el-form-item>
        <el-form-item label="工作" prop="work">
          <el-input v-model="form.work" placeholder="请输入工作" />
        </el-form-item>
        <el-form-item label="省/市" prop="city">
          <el-input v-model="form.city" placeholder="请输入省/市" />
        </el-form-item>
        <el-form-item label="地区" prop="region">
          <el-input v-model="form.region" placeholder="请输入地区" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="银行名称" prop="bankName">
          <el-input v-model="form.bankName" placeholder="请输入银行名称" />
        </el-form-item>
        <el-form-item label="银行卡号" prop="bankCard">
          <el-input v-model="form.bankCard" placeholder="请输入银行卡号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/business/user";

export default {
  name: "User",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户列表表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        email: null,
        status: "",
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      statusList: [
        {
          name: '全部',
          value: ""
        },
        {
          name: '正常',
          value: 1
        },
        {
          name: '停用',
          value: 0
        },
      ],
      genderList:[
        { name: "男", value: 1},
        { name: "女", value: 2},
        { name: "其他", value: 3}
      ],
      // 时间
      dateRange:[],
      pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户列表列表 */
    getList() {
      this.loading = true;
      listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userName: null,
        realName: null,
        loginPwd: null,
        email: null,
        gender: null,
        nationality: null,
        birth: null,
        idCard: null,
        work: null,
        city: null,
        region: null,
        address: null,
        bankName: null,
        bankCard: null,
        status: null,
        registerTime: null,
        registerIp: null,
        lastTime: null,
        lastIp: null,
        modifyTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.id)
    //   this.single = selection.length!==1
    //   this.multiple = !selection.length
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.form.loginPwd = ''
        this.open = true;
        this.title = "修改用户列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除用户列表编号为"' + ids + '"的数据项？').then(function() {
        return delUser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    },
    // 修改冻结状态
    changeStatus(id,status){
      updateUser(
        {
          id: id,
          userStatus : status
        }
      ).then(response => {
        this.$modal.msgSuccess("修改成功");
      });
    },
  }
};
</script>
