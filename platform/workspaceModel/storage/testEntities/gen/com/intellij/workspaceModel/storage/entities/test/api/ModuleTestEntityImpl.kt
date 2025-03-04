// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.workspaceModel.storage.entities.test.api

import com.intellij.workspaceModel.storage.*
import com.intellij.workspaceModel.storage.EntityInformation
import com.intellij.workspaceModel.storage.EntitySource
import com.intellij.workspaceModel.storage.EntityStorage
import com.intellij.workspaceModel.storage.GeneratedCodeApiVersion
import com.intellij.workspaceModel.storage.GeneratedCodeImplVersion
import com.intellij.workspaceModel.storage.ModifiableWorkspaceEntity
import com.intellij.workspaceModel.storage.MutableEntityStorage
import com.intellij.workspaceModel.storage.PersistentEntityId
import com.intellij.workspaceModel.storage.WorkspaceEntity
import com.intellij.workspaceModel.storage.impl.ConnectionId
import com.intellij.workspaceModel.storage.impl.EntityLink
import com.intellij.workspaceModel.storage.impl.ModifiableWorkspaceEntityBase
import com.intellij.workspaceModel.storage.impl.WorkspaceEntityBase
import com.intellij.workspaceModel.storage.impl.WorkspaceEntityData
import com.intellij.workspaceModel.storage.impl.extractOneToManyChildren
import com.intellij.workspaceModel.storage.impl.updateOneToManyChildrenOfParent
import org.jetbrains.deft.ObjBuilder
import org.jetbrains.deft.Type
import org.jetbrains.deft.annotations.Child

@GeneratedCodeApiVersion(1)
@GeneratedCodeImplVersion(1)
open class ModuleTestEntityImpl : ModuleTestEntity, WorkspaceEntityBase() {

  companion object {
    internal val CONTENTROOTS_CONNECTION_ID: ConnectionId = ConnectionId.create(ModuleTestEntity::class.java,
                                                                                ContentRootTestEntity::class.java,
                                                                                ConnectionId.ConnectionType.ONE_TO_MANY, false)
    internal val FACETS_CONNECTION_ID: ConnectionId = ConnectionId.create(ModuleTestEntity::class.java, FacetTestEntity::class.java,
                                                                          ConnectionId.ConnectionType.ONE_TO_MANY, false)

    val connections = listOf<ConnectionId>(
      CONTENTROOTS_CONNECTION_ID,
      FACETS_CONNECTION_ID,
    )

  }

  @JvmField
  var _name: String? = null
  override val name: String
    get() = _name!!

  override val contentRoots: List<ContentRootTestEntity>
    get() = snapshot.extractOneToManyChildren<ContentRootTestEntity>(CONTENTROOTS_CONNECTION_ID, this)!!.toList()

  override val facets: List<FacetTestEntity>
    get() = snapshot.extractOneToManyChildren<FacetTestEntity>(FACETS_CONNECTION_ID, this)!!.toList()

  override fun connectionIdList(): List<ConnectionId> {
    return connections
  }

  class Builder(val result: ModuleTestEntityData?) : ModifiableWorkspaceEntityBase<ModuleTestEntity>(), ModuleTestEntity.Builder {
    constructor() : this(ModuleTestEntityData())

    override fun applyToBuilder(builder: MutableEntityStorage) {
      if (this.diff != null) {
        if (existsInBuilder(builder)) {
          this.diff = builder
          return
        }
        else {
          error("Entity ModuleTestEntity is already created in a different builder")
        }
      }

      this.diff = builder
      this.snapshot = builder
      addToBuilder()
      this.id = getEntityData().createEntityId()

      // Process linked entities that are connected without a builder
      processLinkedEntities(builder)
      checkInitialization() // TODO uncomment and check failed tests
    }

    fun checkInitialization() {
      val _diff = diff
      if (!getEntityData().isNameInitialized()) {
        error("Field ModuleTestEntity#name should be initialized")
      }
      if (!getEntityData().isEntitySourceInitialized()) {
        error("Field ModuleTestEntity#entitySource should be initialized")
      }
      // Check initialization for list with ref type
      if (_diff != null) {
        if (_diff.extractOneToManyChildren<WorkspaceEntityBase>(CONTENTROOTS_CONNECTION_ID, this) == null) {
          error("Field ModuleTestEntity#contentRoots should be initialized")
        }
      }
      else {
        if (this.entityLinks[EntityLink(true, CONTENTROOTS_CONNECTION_ID)] == null) {
          error("Field ModuleTestEntity#contentRoots should be initialized")
        }
      }
      // Check initialization for list with ref type
      if (_diff != null) {
        if (_diff.extractOneToManyChildren<WorkspaceEntityBase>(FACETS_CONNECTION_ID, this) == null) {
          error("Field ModuleTestEntity#facets should be initialized")
        }
      }
      else {
        if (this.entityLinks[EntityLink(true, FACETS_CONNECTION_ID)] == null) {
          error("Field ModuleTestEntity#facets should be initialized")
        }
      }
    }

    override fun connectionIdList(): List<ConnectionId> {
      return connections
    }

    // Relabeling code, move information from dataSource to this builder
    override fun relabel(dataSource: WorkspaceEntity, parents: Set<WorkspaceEntity>?) {
      dataSource as ModuleTestEntity
      this.name = dataSource.name
      this.entitySource = dataSource.entitySource
      if (parents != null) {
      }
    }


    override var name: String
      get() = getEntityData().name
      set(value) {
        checkModificationAllowed()
        getEntityData().name = value
        changedProperty.add("name")
      }

    override var entitySource: EntitySource
      get() = getEntityData().entitySource
      set(value) {
        checkModificationAllowed()
        getEntityData().entitySource = value
        changedProperty.add("entitySource")

      }

    // List of non-abstract referenced types
    var _contentRoots: List<ContentRootTestEntity>? = emptyList()
    override var contentRoots: List<ContentRootTestEntity>
      get() {
        // Getter of the list of non-abstract referenced types
        val _diff = diff
        return if (_diff != null) {
          _diff.extractOneToManyChildren<ContentRootTestEntity>(CONTENTROOTS_CONNECTION_ID, this)!!.toList() + (this.entityLinks[EntityLink(
            true, CONTENTROOTS_CONNECTION_ID)] as? List<ContentRootTestEntity> ?: emptyList())
        }
        else {
          this.entityLinks[EntityLink(true, CONTENTROOTS_CONNECTION_ID)] as? List<ContentRootTestEntity> ?: emptyList()
        }
      }
      set(value) {
        // Setter of the list of non-abstract referenced types
        checkModificationAllowed()
        val _diff = diff
        if (_diff != null) {
          for (item_value in value) {
            if (item_value is ModifiableWorkspaceEntityBase<*> && (item_value as? ModifiableWorkspaceEntityBase<*>)?.diff == null) {
              _diff.addEntity(item_value)
            }
          }
          _diff.updateOneToManyChildrenOfParent(CONTENTROOTS_CONNECTION_ID, this, value)
        }
        else {
          for (item_value in value) {
            if (item_value is ModifiableWorkspaceEntityBase<*>) {
              item_value.entityLinks[EntityLink(false, CONTENTROOTS_CONNECTION_ID)] = this
            }
            // else you're attaching a new entity to an existing entity that is not modifiable
          }

          this.entityLinks[EntityLink(true, CONTENTROOTS_CONNECTION_ID)] = value
        }
        changedProperty.add("contentRoots")
      }

    // List of non-abstract referenced types
    var _facets: List<FacetTestEntity>? = emptyList()
    override var facets: List<FacetTestEntity>
      get() {
        // Getter of the list of non-abstract referenced types
        val _diff = diff
        return if (_diff != null) {
          _diff.extractOneToManyChildren<FacetTestEntity>(FACETS_CONNECTION_ID, this)!!.toList() + (this.entityLinks[EntityLink(true,
                                                                                                                                FACETS_CONNECTION_ID)] as? List<FacetTestEntity>
                                                                                                    ?: emptyList())
        }
        else {
          this.entityLinks[EntityLink(true, FACETS_CONNECTION_ID)] as? List<FacetTestEntity> ?: emptyList()
        }
      }
      set(value) {
        // Setter of the list of non-abstract referenced types
        checkModificationAllowed()
        val _diff = diff
        if (_diff != null) {
          for (item_value in value) {
            if (item_value is ModifiableWorkspaceEntityBase<*> && (item_value as? ModifiableWorkspaceEntityBase<*>)?.diff == null) {
              _diff.addEntity(item_value)
            }
          }
          _diff.updateOneToManyChildrenOfParent(FACETS_CONNECTION_ID, this, value)
        }
        else {
          for (item_value in value) {
            if (item_value is ModifiableWorkspaceEntityBase<*>) {
              item_value.entityLinks[EntityLink(false, FACETS_CONNECTION_ID)] = this
            }
            // else you're attaching a new entity to an existing entity that is not modifiable
          }

          this.entityLinks[EntityLink(true, FACETS_CONNECTION_ID)] = value
        }
        changedProperty.add("facets")
      }

    override fun getEntityData(): ModuleTestEntityData = result ?: super.getEntityData() as ModuleTestEntityData
    override fun getEntityClass(): Class<ModuleTestEntity> = ModuleTestEntity::class.java
  }
}

class ModuleTestEntityData : WorkspaceEntityData.WithCalculablePersistentId<ModuleTestEntity>() {
  lateinit var name: String

  fun isNameInitialized(): Boolean = ::name.isInitialized

  override fun wrapAsModifiable(diff: MutableEntityStorage): ModifiableWorkspaceEntity<ModuleTestEntity> {
    val modifiable = ModuleTestEntityImpl.Builder(null)
    modifiable.allowModifications {
      modifiable.diff = diff
      modifiable.snapshot = diff
      modifiable.id = createEntityId()
      modifiable.entitySource = this.entitySource
    }
    modifiable.changedProperty.clear()
    return modifiable
  }

  override fun createEntity(snapshot: EntityStorage): ModuleTestEntity {
    val entity = ModuleTestEntityImpl()
    entity._name = name
    entity.entitySource = entitySource
    entity.snapshot = snapshot
    entity.id = createEntityId()
    return entity
  }

  override fun persistentId(): PersistentEntityId<*> {
    return ModuleTestEntityPersistentId(name)
  }

  override fun getEntityInterface(): Class<out WorkspaceEntity> {
    return ModuleTestEntity::class.java
  }

  override fun serialize(ser: EntityInformation.Serializer) {
  }

  override fun deserialize(de: EntityInformation.Deserializer) {
  }

  override fun createDetachedEntity(parents: List<WorkspaceEntity>): WorkspaceEntity {
    return ModuleTestEntity(name, entitySource) {
    }
  }

  override fun getRequiredParents(): List<Class<out WorkspaceEntity>> {
    val res = mutableListOf<Class<out WorkspaceEntity>>()
    return res
  }

  override fun equals(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as ModuleTestEntityData

    if (this.name != other.name) return false
    if (this.entitySource != other.entitySource) return false
    return true
  }

  override fun equalsIgnoringEntitySource(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as ModuleTestEntityData

    if (this.name != other.name) return false
    return true
  }

  override fun hashCode(): Int {
    var result = entitySource.hashCode()
    result = 31 * result + name.hashCode()
    return result
  }

  override fun hashCodeIgnoringEntitySource(): Int {
    var result = javaClass.hashCode()
    result = 31 * result + name.hashCode()
    return result
  }
}
