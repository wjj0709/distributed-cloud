package com.phoenix.distributed.authorization.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "岗位信息表")
public class SysPost extends Model<SysPost> {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @Id(value = "id", keyType = KeyType.Auto)
    @Schema(description = "岗位ID")
    private Long id;

    /**
     * 岗位编码
     */
    @NotBlank(message = "岗位编码不能为空")
    @Schema(description = "岗位编码")
    private String postCode;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    @Schema(description = "岗位名称")
    private String postName;

    /**
     * 岗位排序
     */
    @NotNull(message = "排序值不能为空")
    @Schema(description = "岗位排序")
    private Integer postSort;

    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    private String remark;

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
     * 是否删除 -1：已删除 0：正常
     */
    @Schema(description = "是否删除  -1：已删除  0：正常")
    private String delFlag;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
