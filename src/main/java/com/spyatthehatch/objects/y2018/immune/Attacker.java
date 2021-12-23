package com.spyatthehatch.objects.immune;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * Attacker abstract class.
 * 
 * @author Bill Everton
 * @version Advent 2018
 */
public abstract class Attacker implements Group {
	/**
	 * Count of Attacker objects, used for generating ids.
	 */
	private static int count = 0;
	
	/**
	 * Name of Attacker object (type).
	 */
	protected String name;
	
	/**
	 * Id of this Attacker object.
	 */
	protected int id;
	
	/**
	 * The quantity of units this Attacker group has.
	 */
	protected int units;
	
	/**
	 * The number of hit points each unit has.
	 */
	protected int hitPoints;
	
	/**
	 * List of immunities that this Attacker group has.
	 */
	protected List<AttackType> immune;
	
	/**
	 * List of weaknesses this Attacker group has.
	 */
	protected List<AttackType> weak;
	
	/**
	 * Base damage a single unit inflicts.
	 */
	protected int attack;
	
	/**
	 * Type of damage this Attacker group inflicts.
	 */
	protected AttackType attackType;
	
	/**
	 * Initiative value for breaking ties and determining attack order.
	 */
	protected int initiative;
	
	/**
	 * Constructor.
	 * 
	 * @param units Number of units.
	 * @param hitPoints Number of hit points.
	 * @param type AttackType this group has.
	 * @param attack Attack damage per unit.
	 * @param initiative Initiative value.
	 */
	@SuppressWarnings("deprecation")
	protected Attacker(final String s){
		this.id = count++;
		this.units = Integer.valueOf(StringUtils.substringBefore(s, " units"));
		this.hitPoints = Integer.valueOf(StringUtils.substringBetween(s,
			"each with ", " hit points"));
		
		final String clause = StringUtils.substringBetween(s, "hit points",
			"with an attack");
		
		this.immune = new ArrayList<AttackType>();
		this.weak = new ArrayList<AttackType>();
		if(!clause.equals(" ")){
			if(clause.contains(";")){
				String immunities = null;
				String weaknesses = null;
				if(clause.contains("(weak")){
					immunities = StringUtils.substringAfter(clause, ";");
					weaknesses = StringUtils.removeAll(clause, ";[ ,a-z]*");
				} else {
					weaknesses = StringUtils.substringAfter(clause, ";");
					immunities = StringUtils.removeAll(clause, ";[ ,a-z]*");
				}
				
				addImmunities(immunities);
				addWeaknesses(weaknesses);
			} else if(clause.contains("immune to")){
				addImmunities(clause);
			} else if(clause.contains("weak to")){
				addWeaknesses(clause);
			}
		}
		
		final String[] damage = StringUtils.substringBetween(s, "that does ",
			" damage").split(" ");
		this.attack = Integer.valueOf(damage[0]);
		this.attackType = AttackType.fromString(damage[1]);
		
		this.initiative = Integer.valueOf(StringUtils.substringAfter(s,
			"initiative "));
	}
	
	public int getInitiative(){
		return this.initiative;
	}
	
	public void boost(final int increase){
		this.attack += increase;
	}
	
	public int getUnits(){
		if(this.units < 0){
			this.units = 0;
		}
		return this.units;
	}
	
	public int getAttack(){
		return this.attack;
	}
	
	public int getHitPoints(){
		return this.hitPoints;
	}
	
	public AttackType getAttackType(){
		return this.attackType;
	}
	
	public List<AttackType> getWeaknesses(){
		return this.weak;
	}
	
	public List<AttackType> getImmunities(){
		return this.immune;
	}
	
	public boolean decrementUnits(final int killed){
		this.units -= killed;
		return (this.units <= 0);
	}
	
	public boolean attack(final Group defender){
		final int damage = this.getAttackDamange(defender);
		final int killed = damage / defender.getHitPoints();
		return defender.decrementUnits(killed);
	}
	
	public int getAttackDamange(final Group defender){
		final int baseDamage = this.units * this.attack;
		int multiplier = 1;
		
		if(defender.getWeaknesses().contains(this.attackType)){
			multiplier = 2;
		} else if(defender.getImmunities().contains(this.attackType)){
			multiplier = 0;
		}
		
		return baseDamage * multiplier;
	}
	
	@Override
	public int compareTo(final Group other){
		final int power = this.units * this.attack;
		final int otherPower = other.getUnits() * other.getAttack();
		
		if(otherPower - power == 0){
			return other.getInitiative() - this.initiative;
		} else {
			return otherPower - power;
		}
	}
	
	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder();
		
		if(!this.immune.isEmpty()){
			sb.append(", immune:");
			for(final AttackType type : this.immune){
				sb.append(type + " ");
			}
		}
		
		if(!this.weak.isEmpty()){
			sb.append(", weak:");
			for(final AttackType type : this.weak){
				sb.append(type + " ");
			}
		}
		
		return this.name + " id:" + this.id + ", units:" + this.units + ", hp:" +
			this.hitPoints + ", attack:" + this.attack + " " + this.attackType +
			", init:" + this.initiative + sb.toString();
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.id);
	}
	
	private void addImmunities(final String s){
		final String[] immunities = StringUtils.substringBetween(s,
				"immune to ", ")").split(", ");
			
			for(int i=0; i<immunities.length; i++){
				this.immune.add(AttackType.fromString(immunities[i]));
			}
	}
	
	private void addWeaknesses(final String s){
		final String[] weaknesses = StringUtils.substringBetween(s,
				"weak to ", ")").split(", ");
			for(int i=0; i<weaknesses.length; i++){
				this.weak.add(AttackType.fromString(weaknesses[i]));
			}
	}
}