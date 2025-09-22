package com.phoenix.distributed.authorization.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 */
@Data
@Schema(description = "部门")
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    @Id(value = "dept_id", keyType = KeyType.Auto)
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Schema(description = "部门名称")
    private String name;

    /**
     * 排序
     */
    @NotNull(message = "排序值不能为空")
    @Schema(description = "排序值")
    private Integer sortOrder;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String updateBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 父级部门id
     */
    @Schema(description = "父级部门id")
    private Long parentId;

    /**
     * 是否删除 1：已删除 0：正常
     */
    @Schema(description = "删除标记,1:已删除,0:正常")
    private String delFlag;

}
