package shiku.therapy.dao;

import shiku.therapy.entity.Role;

//DAO Pattern for Role
public interface RoleDao {

	public Role findRoleByName(String roleName);
	
}
