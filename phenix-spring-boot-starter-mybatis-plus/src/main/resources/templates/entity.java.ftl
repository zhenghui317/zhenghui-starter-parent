package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.phenix.starter.web.common.serializer.CustomLongSerializer;
import com.phenix.starter.web.common.serializer.CustomLongDeserializer;
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
</#if>

/**
 * <p>
    * ${table.comment!}
    * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if table.convert>
@TableName("${table.name}")
</#if>
<#if swagger2>
@ApiModel(value="${entity}对象", description="${table.comment!}")
</#if>
<#if entityLombokModel>
@Data
	<#if entityBuilderModel>
@Builder
	</#if>
	<#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
	<#else>
@EqualsAndHashCode(callSuper = false)
	</#if>
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#else>
public class ${entity} implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
	<#if field.keyFlag>
		<#assign keyPropertyName="${field.propertyName}"/>
	</#if>

	<#if field.comment!?length gt 0>
		<#if swagger2>
    @ApiModelProperty(value = "${field.comment}")
		<#else>
    /**
     * ${field.comment}
     */
		</#if>
	</#if>
	<#if field.keyFlag>
	<#-- 主键 -->
		<#if field.keyIdentityFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
		<#elseif idType??>
    @TableId(value = "${field.name}", type = IdType.${idType})
		<#elseif field.convert>
    @TableId("${field.name}")
		</#if>
	<#-- 普通字段 -->
	<#elseif field.fill??>
	<#-- -----   存在字段填充设置   ----->
		<#if field.convert>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
		<#else>
    @TableField(fill = FieldFill.${field.fill})
		</#if>
	<#elseif field.convert>
    @TableField("${field.name}")
	</#if>
<#-- 乐观锁注解 -->
	<#if (versionFieldName!"") == field.name>
    @Version
	</#if>
<#-- 逻辑删除注解 -->
	<#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
	</#if>
	<#if field.propertyType == "LocalDate">
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
	<#if field.name == "create_date" || field.name == "create_time">
	@TableField(fill = FieldFill.INSERT)
	<#elseif field.name == "update_date" || field.name == "update_time">
	@TableField(fill = FieldFill.INSERT_UPDATE)
	</#if>
	</#if>
	<#if field.propertyType == "LocalDateTime">
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	<#if field.name == "create_date" || field.name == "create_time">
	@TableField(fill = FieldFill.INSERT)
	<#elseif field.name == "update_date" || field.name == "update_time">
	@TableField(fill = FieldFill.INSERT_UPDATE)
	</#if>
	</#if>
	<#if field.propertyType == "Long">
    @JsonDeserialize(using = CustomLongDeserializer.class)
    @JsonSerialize(using = CustomLongSerializer.class)
	</#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
	<#list table.fields as field>
		<#if field.propertyType == "boolean">
			<#assign getprefix="is"/>
		<#else>
			<#assign getprefix="get"/>
		</#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

		<#if entityBuilderModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
		<#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
		</#if>
        this.${field.propertyName} = ${field.propertyName};
		<#if entityBuilderModel>
        return this;
		</#if>
    }
	</#list>
</#if>

<#if entityColumnConstant>
	<#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

	</#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
	<#if keyPropertyName??>
        return this.${keyPropertyName};
	<#else>
        return null;
	</#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
	<#list table.fields as field>
		<#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
		<#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
		</#if>
	</#list>
        "}";
    }
</#if>
}
