package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Option;

@Remote
public interface OptionBusinessRemote {
	public int addOption(Option option);
	public Option getOptionById(int id);
	public List<Option> getAllOption();
	public void updateOption(Option option);
	public boolean deleteOption(int id);
}
