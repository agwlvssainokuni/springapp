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

package cherry.foundation.batch.mgmt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.bizdtm.BizDateTime;

public class StartBatchTest {

	private BizDateTime bizDateTime;

	private BatchStatusStore batchStatusStore;

	@Before
	public void before() {
		bizDateTime = mock(BizDateTime.class);
		batchStatusStore = mock(BatchStatusStore.class);
	}

	@Test
	public void testExecute_NORMAL() {
		// 準備
		LocalDateTime dtm = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(dtm);
		when(batchStatusStore.isBatchRunning("batchId")).thenReturn(false);
		StartBatch impl = create();
		// 実行
		assertEquals(ExitStatus.NORMAL, impl.execute("batchId"));
		// 検証
		verify(batchStatusStore, times(1)).isBatchRunning(eq("batchId"));
		verify(batchStatusStore, times(1)).updateToRunning(eq("batchId"), eq(dtm));
		verify(bizDateTime, times(1)).now();
	}

	@Test
	public void testExecute_WARN() {
		// 準備
		when(batchStatusStore.isBatchRunning("batchId")).thenReturn(true);
		StartBatch impl = create();
		// 実行
		assertEquals(ExitStatus.WARN, impl.execute("batchId"));
		// 検証
		verify(batchStatusStore, times(1)).isBatchRunning(eq("batchId"));
		verify(batchStatusStore, never()).updateToRunning(anyString(), any(LocalDateTime.class));
		verify(bizDateTime, never()).now();
	}

	@Test
	public void testExecute_ERROR() {
		// 準備
		StartBatch impl = create();
		// 実行
		assertEquals(ExitStatus.ERROR, impl.execute());
		// 検証
		verify(batchStatusStore, never()).isBatchRunning(anyString());
		verify(batchStatusStore, never()).updateToRunning(anyString(), any(LocalDateTime.class));
		verify(bizDateTime, never()).now();
	}

	private StartBatch create() {
		BatchStatusServiceImpl impl = new BatchStatusServiceImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setBatchStatusStore(batchStatusStore);
		StartBatch batch = new StartBatch();
		batch.setBatchStatusService(impl);
		return batch;
	}

}
