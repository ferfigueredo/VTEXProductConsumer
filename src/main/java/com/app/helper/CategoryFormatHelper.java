package com.app.helper;

import java.util.ArrayList;
import java.util.List;

import com.app.mapper.CategoriaMapper;

public class CategoryFormatHelper {

	public static List<String> categoriasToStringList(List<CategoriaMapper> categorias) {
		List<String> categoriasIds = new ArrayList<>();

		for (CategoriaMapper cate : categorias) {
			if (cate.getHasChildren()) {
				for (CategoriaMapper child : cate.getChildren()) {
					if (child.getHasChildren()) {
						for (CategoriaMapper child2 : child.getChildren()) {
							if (!child2.getHasChildren()) {
								String idCategoriaFinal = "/" + cate.getId() + "/" + child.getId() + "/"
										+ child2.getId() + "/";
								categoriasIds.add(idCategoriaFinal);
							}
						}
					} else {
						String idCategoriaFinal = "/" + cate.getId() + "/" + child.getId() + "/";
						categoriasIds.add(idCategoriaFinal);
					}
				}
			} else {
				String idCategoriaFinal = "/" + cate.getId() + "/";
				categoriasIds.add(idCategoriaFinal);
			}

		}
//		for (String string : categoriasIds) {
//			System.out.println(string);
//		}
		return categoriasIds;
	}

}
