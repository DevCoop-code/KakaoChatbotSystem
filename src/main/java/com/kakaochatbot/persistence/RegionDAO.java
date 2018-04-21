package com.kakaochatbot.persistence;

import java.util.List;

public interface RegionDAO 
{
	public List<String> read_large() throws Exception;
}
