package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ddv
 * @date : 2019/2/28 上午1:33
 */

public class Main {

	private List<Integer> list;

	public Main() {
	}

	public List<Integer> getList() {
		return list;
	}

	public Main setList(List<Integer> list) {
		this.list = list;
		return this;
	}

	public Main(List<Integer> list) {
		this.list = list;
	}


	public void get(){
		System.out.println(list.get(0));
	}

	public static void main(String[] args){
	    Main main = new Main();
	    List<Integer>  list = new ArrayList<Integer>();
	    list.add(1);
		main.setList(list).get();
	}

}
