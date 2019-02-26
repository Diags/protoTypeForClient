import { TestBed, async, inject } from '@angular/core/testing';

import { TaskDetailGuard } from './task-detail.guard';

describe('TaskDetailGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TaskDetailGuard]
    });
  });

  it('should ...', inject([TaskDetailGuard], (guard: TaskDetailGuard) => {
    expect(guard).toBeTruthy();
  }));
});
