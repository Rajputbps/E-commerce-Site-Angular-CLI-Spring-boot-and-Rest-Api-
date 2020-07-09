import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MordernComponent } from './mordern.component';

describe('MordernComponent', () => {
  let component: MordernComponent;
  let fixture: ComponentFixture<MordernComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MordernComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MordernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
