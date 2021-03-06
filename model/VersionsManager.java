package model;

import javax.swing.JOptionPane;

import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy strategy;
	private String strategyType;

	
	public VersionsManager(VersionsStrategy versionsStrategy) {
		this.strategy = versionsStrategy;
	}
	
	public void setStrategyType(String type) {
		this.strategyType = type;
		enable();
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}
	
	public void setStrategy(VersionsStrategy strategy) {
		this.strategy = strategy;
		enable();
	}
	
	public Document setPreviousVersion() {
		return null;
	}

	public void enableStrategy() {
		// TODO Auto-generated method stub
		
		if(strategyType.equals("volatile") && strategy instanceof VolatileVersionsStrategy) {
			enable();
		}
		else if(strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("stable") && strategy instanceof StableVersionsStrategy) {
			enable();
		}
	}

	public void changeStrategy() {
		// TODO Auto-generated method stub
		if(strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			enable();
		}
	}

	public void  putVersion(Document document) {
		// TODO Auto-generated method stub
		strategy.putVersion(document);
	}

	public Document rollback() {
		// TODO Auto-generated method stub
		if(isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		else {
			Document doc = strategy.getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				strategy.removeVersion();
			}
			return doc;
		}
		
	}

	public VersionsStrategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}
}
