package com.alonelaval.acegi.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author huawei
 * 
 */
public class BeanUtil {
	private static Logger logger = null;
	private static List<Class> classes = new ArrayList<Class>();
	static {
		classes.add(Double.class);
		classes.add(double.class);
		classes.add(int.class);
		classes.add(Integer.class);
		classes.add(float.class);
		classes.add(Float.class);
		classes.add(char.class);
		classes.add(Character.class);
		classes.add(byte.class);
		classes.add(Byte.class);
		classes.add(Short.class);
		classes.add(short.class);
		classes.add(boolean.class);
		classes.add(Boolean.class);
		classes.add(long.class);
		classes.add(Long.class);
		classes.add(BigDecimal.class);
		classes.add(BigInteger.class);
		classes.add(String.class);
		Class class1 = new BeanUtil().getClass();
		logger = LoggerFactory.getLogger(class1);
	}

	/**
	 * 将一个bean属性值Copy到另外一个Bean的属性中 前提是当前bean中的属性名称跟目标bean属性名称一致 才会Copy过去
	 * source是来源 target是目标
	 * 
	 * @param <T>
	 * @return target
	 * @param target
	 * @param source
	 */
	public static <T> T copyProPerties(T target, Object source) {
		try {
			BeanUtils.copyProperties(target, source);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) target;
	}
	
	/**
	 * 将一个集合中的的类型的属性值Copy到一个目标类型的属性中 返回一个目标类型集合
	 * 
	 * @param <T>
	 * @param targer
	 * @param source
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> copyPropertiesForCollection(T target,
			Collection source) {
		java.util.List<T> list = new ArrayList<T>();

		if (null != target && null != source && !source.isEmpty()) {

			for (Object obj : source) {
				Object object = null;
				try {
					object = target.getClass().newInstance();
				} catch (InstantiationException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				}
				object = copyProPerties(object, obj);
				list.add((T) object);
			}

			return list;
		}

		return list;
	}

	/**
	 * copy 将一个map里的元素，按照Key的名称，
	 * 通过反射将值 copy到一个类型的属性中，
	 * 前提是Key的名称跟类型中的SET方法名称
	 * 后缀必须一致
	 * 
	 * @param <T>
	 * @param target
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copyPropertiesForMap(T target, Map<String, ?> map) {

		Class cls = target.getClass();
		Method[] methods = cls.getDeclaredMethods();
		List<Method> list = new ArrayList<Method>();

		for (Method method : methods) {
			if (method.getName().toUpperCase().startsWith("SET")) {
				list.add(method);
			}
		}
		Object obj = null;
		try {
			obj = target.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			for (Method method : list) {
				if (method.getName().toUpperCase().endsWith(
						("SET" + entry.getKey()).toUpperCase())
						&& entry.getValue() != null) {
					try {

						StringBuilder sBuilder = new StringBuilder();
						sBuilder.append("get").append(
								entry.getKey().toString().substring(0, 1)
										.toUpperCase()).append(
								entry.getKey().toString().substring(1,
										entry.getKey().toString().length()));

						Method me = cls.getMethod(sBuilder.toString(), null);
						if (me != null) {
							if (classes.contains(me.getReturnType())) {
								Object objects = convert(me.getReturnType(),
										entry.getValue());
								method.invoke(obj, objects);
								break;
							} else {
								method.invoke(obj, entry.getValue());
								break;
							}
						}

						/*
						 * if (entry.getValue() instanceof BigDecimal ||
						 * entry.getValue() instanceof BigInteger ) { Method me =
						 * cls.getMethod( "get" + entry.getKey().toString()
						 * .substring(0, 1) .toUpperCase() +
						 * entry.getKey().toString() .substring( 1,
						 * entry.getKey() .toString() .length()), null);
						 * if(me.getReturnType() != BigInteger.class &&
						 * me.getReturnType() != BigDecimal.class){ Object[]
						 * objects = convert(me.getReturnType(),
						 * entry.getValue()); method.invoke(obj, objects);
						 * break; } if(me.getReturnType() == BigDecimal.class) {
						 * method.invoke(obj, new
						 * BigDecimal(entry.getValue().toString())); break; }
						 * if(me.getReturnType() == BigInteger.class) {
						 * method.invoke(obj, new
						 * BigInteger(entry.getValue().toString())); break; }
						 * 
						 * }else{ method.invoke(obj, entry.getValue()); break; }
						 */
					} catch (Exception e) {
						//System.out.println("你的类型不对");
						logger.info("convert error!" + e.toString());
					}

				}

			}
		}
		return (T) obj;
	}

	public static Object convert(Class cls, Object object) throws Exception {

		Object[] objects = new Object[1];
		if (cls == Double.class || cls == double.class) {
			objects[0] = Double.parseDouble(object.toString());
		}
		if (cls == Integer.class || cls == int.class) {
			objects[0] = Integer.parseInt(object.toString());
		}
		if (cls == Long.class || cls == long.class) {
			objects[0] = Long.parseLong(object.toString());
		}
		if (cls == Float.class || cls == float.class) {
			objects[0] = Float.parseFloat(object.toString());
		}
		if (cls == Byte.class || cls == byte.class) {
			objects[0] = Byte.parseByte(object.toString());
		}
		if (cls == Character.class || cls == char.class) {
			CharacterIterator sta = new StringCharacterIterator(object
					.toString());
			objects[0] = Character.valueOf(sta.last());
		}
		if (cls == Short.class || cls == short.class) {
			objects[0] = Short.parseShort(object.toString());
		}
		if (cls == Boolean.class || cls == boolean.class) {
			objects[0] = Boolean.parseBoolean(object.toString());
		}
		if (cls == BigDecimal.class) {
			objects[0] = new BigDecimal(object.toString());
		}
		if (cls == BigInteger.class) {
			objects[0] = new BigInteger(object.toString());
		}
		if (cls == String.class) {
			objects[0] = object.toString();
		}
		return objects[0];
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		BeanUtil beanUtil = new BeanUtil();
		Test test = new Test();
		Test2 test2 = new Test2();
		test2.setNameString("wei");
		map.put("aaaLong", new BigDecimal(1000D));
		map.put("aaaString", "huawei");
		map.put("integer", new BigInteger("11111111"));
		map.put("double1", new BigDecimal(1000D));
		map.put("bigDecimal", new BigDecimal(1000.00));
		map.put("by", new Byte("11"));
		map.put("test2", test2);
		map.put("character", new String("华卫"));
		map.put("bigInteger", new BigInteger("1111111111111111111111"));
		map.put("date", new Date());
		map.put("boolean1", "aaa");
		map.put("float1", "10000");
		// map.put("int1", 11);
		// / new javaTmath.MathContext
		int i = 10000;
		map.put("int1", new BigDecimal("111"));

		Long currentTimeLong = System.currentTimeMillis();
		System.out.println("开始" + currentTimeLong);
		Test t = copyPropertiesForMap(test, map);
		System.out.println("耗时："
				+ (System.currentTimeMillis() - currentTimeLong));

		System.out.println("String" + t.getAaaString());
		System.out.println("BigDecimal" + t.getBigDecimal());
		System.out.println("Intger" + t.getInteger());
		System.out.println("Double" + t.getDouble1());
		System.out.println("LONG" + t.getAaaLong());
		System.out.println("by" + t.getBy());
		System.out.println("test2" + t.getTest2().getNameString());
		System.out.println("int" + t.getInt1());
		System.out.println("character" + t.getCharacter());
		System.out.println("bigInteger" + t.getBigInteger());
		System.out.println("date        " + t.getDate());
		System.out.println("boolean1" + t.getBoolean1());
		System.out.println("float1"+t.getFloat1());
		// System.out.println(new BigInteger("1111111111111111111111"));
		// System.out.println(Integer.class.equals(int.class));

	}

	// 测试
	//	
	// public static void main(String argString []){
	// List<TOrdStRtnOrder> list = new ArrayList<TOrdStRtnOrder>();
	//		
	// for (int i = 0; i < 20; i++) {
	// TOrdStRtnOrder tRtnOrder = new TOrdStRtnOrder();
	// tRtnOrder.setAmount((double)i);
	// tRtnOrder.setOrderId((long)i);
	// tRtnOrder.setOrderNo(String.valueOf(i));
	// tRtnOrder.setOrderStatus(String.valueOf(i));
	// tRtnOrder.setRtnDate(new Date());
	// tRtnOrder.setVendorRetailerName(String.valueOf(i));
	// tRtnOrder.setLastRtnDate(new Date());
	// tRtnOrder.setSingleQty((double)i);
	// tRtnOrder.setCategoryCode(String.valueOf(i));
	// tRtnOrder.setCreateBy(String.valueOf(i));
	// list.add(tRtnOrder);
	// }
	//		
	//		
	// List<StRtnOrderDto> list2 = copyPropertiesForCollection(new
	// StRtnOrderDto(),list);
	// System.out.println(list);
	// System.out.println(list2);
	//
	// for(StRtnOrderDto stDto : list2){
	// System.out.println("orderNO"+stDto.getOrderNo());
	// System.out.println("orderStatus"+stDto.getOrderStatus());
	// System.out.println("vendorRetailerName"+stDto.getVendorRetailerName());
	// System.out.println("amount"+stDto.getAmount());
	// System.out.println("lastRtnDate"+stDto.getLastRtnDate());
	// System.out.println("orderId"+stDto.getOrderId());
	// System.out.println("rtnDate"+stDto.getRtnDate());
	// System.out.println("singleQty"+stDto.getSingleQty());
	// }
	//			
	// }

}

class Test2 {
	private String nameString;

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

}

class Test {
	private Boolean boolean1;
	private BigInteger bigInteger;
	private Character character;
	private int int1;
	private Test2 test2;
	private Byte by;
	private Long aaaLong = 0l;
	private String aaaString = "";
	private Integer integer = 0;
	private Double double1 = 0.00;
	private BigDecimal bigDecimal = new BigDecimal(0.00);
	private Date date;
	private float float1;

	public float getFloat1() {
		return float1;
	}

	public void setFloat1(float float1) {
		this.float1 = float1;
	}

	public Long getAaaLong() {
		return aaaLong;
	}

	public void setAaaLong(Long aaaLong) {
		this.aaaLong = aaaLong;
	}

	public String getAaaString() {
		return aaaString;
	}

	public void setAaaString(String aaaString) {
		this.aaaString = aaaString;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public Double getDouble1() {
		return double1;
	}

	public void setDouble1(Double double1) {
		this.double1 = double1;
	}

	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}

	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}

	public Byte getBy() {
		return by;
	}

	public void setBy(Byte by) {
		this.by = by;
	}

	public Test2 getTest2() {
		return test2;
	}

	public void setTest2(Test2 test2) {
		this.test2 = test2;
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public BigInteger getBigInteger() {
		return bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getBoolean1() {
		return boolean1;
	}

	public void setBoolean1(Boolean boolean1) {
		this.boolean1 = boolean1;
	}

}
