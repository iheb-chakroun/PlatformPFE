package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Option;

@Local
public interface OptionBusinessLocal {
	public int addOption(Option option);
	public Option getOptionById(int id);
	public List<Option> getAllOption();
	public void updateOption(Option option);
	public boolean deleteOption(int id);

}
