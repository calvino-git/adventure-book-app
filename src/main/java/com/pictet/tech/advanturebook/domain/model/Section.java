package com.pictet.tech.advanturebook.domain.model;

import com.pictet.tech.advanturebook.domain.enums.SectionType;

import java.util.List;

public record Section(int id, String text, SectionType type, List<Option> options) {
}
