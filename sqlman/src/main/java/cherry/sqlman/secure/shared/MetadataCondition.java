/*
 * Copyright 2014,2015 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.sqlman.secure.shared;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDateTime;

import cherry.foundation.type.FlagCode;
import cherry.sqlman.SqlType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class MetadataCondition {

	private String name;

	private List<SqlType> sqlType;

	private List<FlagCode> publishedFlg;

	private LocalDateTime registeredFrom;

	private LocalDateTime registeredTo;

	private String loginId;

}
