package com.spyatthehatch.objects.y2018.immune;

import java.util.List;

/**
 * Group interface for all Attacker objects (Immunity and Infection objects).
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public interface Group extends Comparable<Group> {
	/**
	 * Get the initiative value for this object.
	 * 
	 * @return Initiative value for determining attack order or breaking ties.
	 */
	public int getInitiative();
	
	/**
	 * Get the number of units this Group has remaining.
	 * 
	 * @return Quantity of unit objects.
	 */
	public int getUnits();
	
	/**
	 * Get the damage that a single unit inflicts.
	 * 
	 * @return Damage value a single unit inflicts.
	 */
	public int getAttack();
	
	/**
	 * Get the hit points of a single unit for this Group.
	 * 
	 * @return Value of single unit hit points.
	 */
	public int getHitPoints();
	
	/**
	 * Get the AttackType this Group inflicts as damage.
	 * 
	 * @return AttackType this Group inflicts.
	 */
	public AttackType getAttackType();
	
	/**
	 * Get the List of weaknesses for this Group.
	 * 
	 * @return List of weaknesses.
	 */
	public List<AttackType> getWeaknesses();
	
	/**
	 * Get the List of immunities for this Group.
	 * 
	 * @return List of immunities.
	 */
	public List<AttackType> getImmunities();
	
	/**
	 * Inflict damage onto the defender Group object.
	 * 
	 * @param defender Defending Group.
	 * @return True, if this Group has no remaining units.  False, otherwise.
	 */
	public boolean attack(final Group defender);
	
	/**
	 * Decrement the number of units for this Group.
	 * 
	 * @param killed Number of units that were killed.
	 * @return True, if this Group has no remaining units.  False, otherwise.
	 */
	public boolean decrementUnits(int killed);
	
	/**
	 * Adjust the Group attack value.
	 * 
	 * @param increase Amount to increase the attack value by.
	 */
	public void boost(int increase);
	
	/**
	 * <p>Get the attack damage that this Group would inflict on a defending
	 * group.  The attacking Group object's AttackType and the defending Group
	 * object's weaknesses and immunities are considered when determining the
	 * total damage inflicted.</p>
	 * <p><b>NOTE:</b>The damage is not incurred by the defender in this method,
	 * but is rather a prediction of the damage that would be incurred.</p>
	 * 
	 * @param defender Defending object.
	 * @return Total damage that would be inflicted.
	 */
	public int getAttackDamange(Group defender);
}