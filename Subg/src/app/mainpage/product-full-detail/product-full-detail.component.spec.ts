import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductFullDetailComponent } from './product-full-detail.component';

describe('ProductFullDetailComponent', () => {
  let component: ProductFullDetailComponent;
  let fixture: ComponentFixture<ProductFullDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductFullDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductFullDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
