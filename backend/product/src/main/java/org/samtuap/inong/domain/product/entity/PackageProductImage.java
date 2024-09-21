package org.samtuap.inong.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.samtuap.inong.domain.common.BaseEntity;
import org.samtuap.inong.domain.farm.entity.Farm;

@Getter
@Entity
@SQLDelete(sql = "UPDATE package_product_image SET deleted_at = now() WHERE id = ?")
@SQLRestriction("deleted_at is NULL")
public class PackageProductImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_product_id")
    private PackageProduct packageProduct;

    @Column(columnDefinition = "varchar(5000)")
    private String imageUrl;
}
