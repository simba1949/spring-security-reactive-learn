package vip.openpark.authentication.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author anthony
 * @version 2024/2/19 9:26
 */
@Data
@Table("role")
public class RoleEntity {
	
	private transient List<PermissionEntity> permissionEntityList;
	
	/**
	 * 主键
	 */
	@Id
	private Long id;
	
	/**
	 * 业务编码
	 */
	@Column("code")
	private String code;
	
	/**
	 * 角色名称
	 */
	@Column("name")
	private String name;
	
	/**
	 * 是否启用，0表示否，1表示是
	 */
	@Column("bl_enable")
	private Byte blEnable;
	
	/**
	 * 是否删除，0表示否，1表示是
	 */
	@Column("bl_delete")
	private Byte blDelete;
	
	/**
	 * 版本号
	 */
	@Column("version")
	private Long version;
	
	/**
	 * 创建时间
	 */
	@Column("gmt_create")
	private LocalDateTime gmtCreate;
	
	/**
	 * 创建人真实姓名
	 */
	@Column("creator")
	private String creator;
	
	/**
	 * 创建人ID
	 */
	@Column("creator_id")
	private Long creatorId;
	
	/**
	 * 创建人code
	 */
	@Column("creator_code")
	private String creatorCode;
	
	/**
	 * 修改时间
	 */
	@Column("gmt_modify")
	private LocalDateTime gmtModify;
	
	/**
	 * 修改人真实姓名
	 */
	@Column("modifier")
	private String modifier;
	
	/**
	 * 修改人ID
	 */
	@Column("modifier_id")
	private Long modifierId;
	
	/**
	 * 修改人code
	 */
	@Column("modifier_code")
	private String modifierCode;
}