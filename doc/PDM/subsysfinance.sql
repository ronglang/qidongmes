/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 10                       */
/* Created on:     2017/4/10 16:56:46                           */
/*==============================================================*/


if exists(
   select 1 from sys.systable 
   where table_name='fin_accounts_receivable'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_accounts_receivable
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_advance_charge'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_advance_charge
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_aging_analysis'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_aging_analysis
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_customer_reconciliation'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_customer_reconciliation
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_payable'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_payable
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_receivable'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_receivable
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_statistical_invoice'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_statistical_invoice
end if;

if exists(
   select 1 from sys.systable 
   where table_name='fin_supplier_reconciliation'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table fin_supplier_reconciliation
end if;

/*==============================================================*/
/* Table: fin_accounts_receivable                               */
/*==============================================================*/
create table fin_accounts_receivable 
(
   ar_id                int                            not null,
   kingdee_name         varchar                        null,
   ar_code              varchar                        null,
   customer_code        varchar                        null,
   last_year_carryover  varchar                        null,
   month                varchar                        null,
   total                double                         null,
   method_deposit       varchar                        null,
   receivables          varchar                        null,
   constraint PK_FIN_ACCOUNTS_RECEIVABLE primary key clustered (ar_id)
);

comment on table fin_accounts_receivable is 
'预测账款收入情况，包含：客户公司，付款方式等';

comment on column fin_accounts_receivable.customer_code is 
'客户简称';

/*==============================================================*/
/* Table: fin_advance_charge                                    */
/*==============================================================*/
create table fin_advance_charge 
(
   ac_id                int                            not null,
   kingdee_name         varchar                        null,
   supplier_code        varchar                        null,
   responsible_department varchar                        null,
   material             varchar                        null,
   last_year_carryover  double                         null,
   month                varchar                        null,
   total                double                         null,
   rush_back            varchar                        null,
   remarks              varchar                        null,
   ac_code              varchar                        null,
   constraint PK_FIN_ADVANCE_CHARGE primary key clustered (ac_id)
);

comment on table fin_advance_charge is 
'预测付款包含：材料类型，付款方式，供应商等';

comment on column fin_advance_charge.supplier_code is 
'供应商简称';

/*==============================================================*/
/* Table: fin_aging_analysis                                    */
/*==============================================================*/
create table fin_aging_analysis 
(
   aa_id                int                            not null,
   overdue_amount       double                         null,
   over_scope           varchar                        null,
   aa_code              char(10)                       null,
   constraint PK_FIN_AGING_ANALYSIS primary key clustered (aa_id)
);

comment on table fin_aging_analysis is 
'统计逾期后的逾期金额';

/*==============================================================*/
/* Table: fin_customer_reconciliation                           */
/*==============================================================*/
create table fin_customer_reconciliation 
(
   cr_id                int                            not null,
   customer_code        varchar                        null,
   reconciliation_date  date                           null,
   delivery_date        date                           null,
   order_code           int                            null,
   product_name         varchar                        null,
   product_type         varchar                        null,
   product_count        int                            null,
   product_price        double                         null,
   total                double                         null,
   unit                 varchar                        null,
   supplier             varchar                        null,
   supplier_contacts    varchar                        null,
   supplier_fax         varchar                        null,
   remarks              varchar                        null,
   cr_code              varchar                        null,
   constraint PK_FIN_CUSTOMER_RECONCILIATION primary key clustered (cr_id)
);

comment on table fin_customer_reconciliation is 
'客户对账单，用于客户对比账单，包括数量，单价，产品类型';

comment on column fin_customer_reconciliation.customer_code is 
'客户简称';

/*==============================================================*/
/* Table: fin_payable                                           */
/*==============================================================*/
create table fin_payable 
(
   pay_id               int                            not null,
   supplier_code        varchar                        null,
   kingdee_name         varchar                        null,
   responsible_department varchar                        null,
   material_name        varchar                        null,
   last_year_carryover  double                         null,
   month                varchar                        null,
   total                double                         null,
   payment_method       varchar                        null,
   payment              varchar                        null,
   remarks              varchar                        null,
   pay_code             varchar                        null,
   constraint PK_FIN_PAYABLE primary key clustered (pay_id)
);

comment on table fin_payable is 
'应付款明细表，统计供应商名，付款方式，材料名称等';

comment on column fin_payable.supplier_code is 
'供应商简称';

/*==============================================================*/
/* Table: fin_receivable                                        */
/*==============================================================*/
create table fin_receivable 
(
   rec_id               int                            not null,
   rec_code             varchar                        null,
   kingdee_name         varchar                        null,
   customer_code        varchar                        null,
   月份                   varchar                        null,
   total                double                         null,
   method_deposit       varchar                        null,
   receivables          varchar                        null,
   constraint PK_FIN_RECEIVABLE primary key clustered (rec_id)
);

comment on table fin_receivable is 
'统计客户名称，付款方式，产品类型，数量，单价，付款时间，对付款客户进行催账处理';

comment on column fin_receivable.customer_code is 
'客户简称';

/*==============================================================*/
/* Table: fin_statistical_invoice                               */
/*==============================================================*/
create table fin_statistical_invoice 
(
   si_id                int                            not null,
   si_code              varchar                        null,
   payment_date         date                           null,
   document_size        varchar                        null,
   department_code      varchar                        null,
   company              varchar                        null,
   unit                 varchar                        null,
   first_level_subjects varchar                        null,
   second_level_subjects varchar                        null,
   expense_total        double                         null,
   invoice_total        double                         null,
   invoice_recovery_date date                           null,
   constraint PK_FIN_STATISTICAL_INVOICE primary key clustered (si_id)
);

comment on table fin_statistical_invoice is 
'统计各个部门报销所用的发票，发票回收日期等';

comment on column fin_statistical_invoice.department_code is 
'请款部门名称';

/*==============================================================*/
/* Table: fin_supplier_reconciliation                           */
/*==============================================================*/
create table fin_supplier_reconciliation 
(
   sr_id                int                            not null,
   supplier_code        varchar                        null,
   delivery_code        int                            null,
   materia_name         varchar                        null,
   materia_type         varchar                        null,
   materia_price        double                         null,
   materia_count        int                            null,
   single_man           varchar                        null,
   single_date          date                           null,
   supplier_tel         varchar                        null,
   unit                 varchar                        null,
   remarks              varchar                        null,
   sr_code              varchar                        null,
   constraint PK_FIN_SUPPLIER_RECONCILIATION primary key clustered (sr_id)
);

comment on table fin_supplier_reconciliation is 
'供应商对账单，用于对供应商核对，产品型号，数量，单价。';

comment on column fin_supplier_reconciliation.supplier_code is 
'供应商简称';

