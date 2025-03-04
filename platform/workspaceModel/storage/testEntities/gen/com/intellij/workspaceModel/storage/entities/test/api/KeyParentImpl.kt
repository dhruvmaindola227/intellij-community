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
open class KeyParentImpl : KeyParent, WorkspaceEntityBase() {

  companion object {
    internal val CHILDREN_CONNECTION_ID: ConnectionId = ConnectionId.create(KeyParent::class.java, KeyChild::class.java,
                                                                            ConnectionId.ConnectionType.ONE_TO_MANY, false)

    val connections = listOf<ConnectionId>(
      CHILDREN_CONNECTION_ID,
    )

  }

  @JvmField
  var _keyField: String? = null
  override val keyField: String
    get() = _keyField!!

  @JvmField
  var _notKeyField: String? = null
  override val notKeyField: String
    get() = _notKeyField!!

  override val children: List<KeyChild>
    get() = snapshot.extractOneToManyChildren<KeyChild>(CHILDREN_CONNECTION_ID, this)!!.toList()

  override fun connectionIdList(): List<ConnectionId> {
    return connections
  }

  class Builder(val result: KeyParentData?) : ModifiableWorkspaceEntityBase<KeyParent>(), KeyParent.Builder {
    constructor() : this(KeyParentData())

    override fun applyToBuilder(builder: MutableEntityStorage) {
      if (this.diff != null) {
        if (existsInBuilder(builder)) {
          this.diff = builder
          return
        }
        else {
          error("Entity KeyParent is already created in a different builder")
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
      if (!getEntityData().isKeyFieldInitialized()) {
        error("Field KeyParent#keyField should be initialized")
      }
      if (!getEntityData().isEntitySourceInitialized()) {
        error("Field KeyParent#entitySource should be initialized")
      }
      if (!getEntityData().isNotKeyFieldInitialized()) {
        error("Field KeyParent#notKeyField should be initialized")
      }
      // Check initialization for list with ref type
      if (_diff != null) {
        if (_diff.extractOneToManyChildren<WorkspaceEntityBase>(CHILDREN_CONNECTION_ID, this) == null) {
          error("Field KeyParent#children should be initialized")
        }
      }
      else {
        if (this.entityLinks[EntityLink(true, CHILDREN_CONNECTION_ID)] == null) {
          error("Field KeyParent#children should be initialized")
        }
      }
    }

    override fun connectionIdList(): List<ConnectionId> {
      return connections
    }

    // Relabeling code, move information from dataSource to this builder
    override fun relabel(dataSource: WorkspaceEntity, parents: Set<WorkspaceEntity>?) {
      dataSource as KeyParent
      this.keyField = dataSource.keyField
      this.entitySource = dataSource.entitySource
      this.notKeyField = dataSource.notKeyField
      if (parents != null) {
      }
    }


    override var keyField: String
      get() = getEntityData().keyField
      set(value) {
        checkModificationAllowed()
        getEntityData().keyField = value
        changedProperty.add("keyField")
      }

    override var entitySource: EntitySource
      get() = getEntityData().entitySource
      set(value) {
        checkModificationAllowed()
        getEntityData().entitySource = value
        changedProperty.add("entitySource")

      }

    override var notKeyField: String
      get() = getEntityData().notKeyField
      set(value) {
        checkModificationAllowed()
        getEntityData().notKeyField = value
        changedProperty.add("notKeyField")
      }

    // List of non-abstract referenced types
    var _children: List<KeyChild>? = emptyList()
    override var children: List<KeyChild>
      get() {
        // Getter of the list of non-abstract referenced types
        val _diff = diff
        return if (_diff != null) {
          _diff.extractOneToManyChildren<KeyChild>(CHILDREN_CONNECTION_ID, this)!!.toList() + (this.entityLinks[EntityLink(true,
                                                                                                                           CHILDREN_CONNECTION_ID)] as? List<KeyChild>
                                                                                               ?: emptyList())
        }
        else {
          this.entityLinks[EntityLink(true, CHILDREN_CONNECTION_ID)] as? List<KeyChild> ?: emptyList()
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
          _diff.updateOneToManyChildrenOfParent(CHILDREN_CONNECTION_ID, this, value)
        }
        else {
          for (item_value in value) {
            if (item_value is ModifiableWorkspaceEntityBase<*>) {
              item_value.entityLinks[EntityLink(false, CHILDREN_CONNECTION_ID)] = this
            }
            // else you're attaching a new entity to an existing entity that is not modifiable
          }

          this.entityLinks[EntityLink(true, CHILDREN_CONNECTION_ID)] = value
        }
        changedProperty.add("children")
      }

    override fun getEntityData(): KeyParentData = result ?: super.getEntityData() as KeyParentData
    override fun getEntityClass(): Class<KeyParent> = KeyParent::class.java
  }
}

class KeyParentData : WorkspaceEntityData<KeyParent>() {
  lateinit var keyField: String
  lateinit var notKeyField: String

  fun isKeyFieldInitialized(): Boolean = ::keyField.isInitialized
  fun isNotKeyFieldInitialized(): Boolean = ::notKeyField.isInitialized

  override fun wrapAsModifiable(diff: MutableEntityStorage): ModifiableWorkspaceEntity<KeyParent> {
    val modifiable = KeyParentImpl.Builder(null)
    modifiable.allowModifications {
      modifiable.diff = diff
      modifiable.snapshot = diff
      modifiable.id = createEntityId()
      modifiable.entitySource = this.entitySource
    }
    modifiable.changedProperty.clear()
    return modifiable
  }

  override fun createEntity(snapshot: EntityStorage): KeyParent {
    val entity = KeyParentImpl()
    entity._keyField = keyField
    entity._notKeyField = notKeyField
    entity.entitySource = entitySource
    entity.snapshot = snapshot
    entity.id = createEntityId()
    return entity
  }

  override fun getEntityInterface(): Class<out WorkspaceEntity> {
    return KeyParent::class.java
  }

  override fun serialize(ser: EntityInformation.Serializer) {
  }

  override fun deserialize(de: EntityInformation.Deserializer) {
  }

  override fun createDetachedEntity(parents: List<WorkspaceEntity>): WorkspaceEntity {
    return KeyParent(keyField, notKeyField, entitySource) {
    }
  }

  override fun getRequiredParents(): List<Class<out WorkspaceEntity>> {
    val res = mutableListOf<Class<out WorkspaceEntity>>()
    return res
  }

  override fun equals(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as KeyParentData

    if (this.keyField != other.keyField) return false
    if (this.entitySource != other.entitySource) return false
    if (this.notKeyField != other.notKeyField) return false
    return true
  }

  override fun equalsIgnoringEntitySource(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as KeyParentData

    if (this.keyField != other.keyField) return false
    if (this.notKeyField != other.notKeyField) return false
    return true
  }

  override fun hashCode(): Int {
    var result = entitySource.hashCode()
    result = 31 * result + keyField.hashCode()
    result = 31 * result + notKeyField.hashCode()
    return result
  }

  override fun hashCodeIgnoringEntitySource(): Int {
    var result = javaClass.hashCode()
    result = 31 * result + keyField.hashCode()
    result = 31 * result + notKeyField.hashCode()
    return result
  }

  override fun equalsByKey(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as KeyParentData

    if (this.keyField != other.keyField) return false
    if (this.entitySource != other.entitySource) return false
    return true
  }

  override fun hashCodeByKey(): Int {
    var result = javaClass.hashCode()
    result = 31 * result + keyField.hashCode()
    result = 31 * result + entitySource.hashCode()
    return result
  }
}
