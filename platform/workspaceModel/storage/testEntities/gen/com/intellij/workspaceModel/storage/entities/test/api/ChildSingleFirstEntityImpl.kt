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
import com.intellij.workspaceModel.storage.impl.extractOneToAbstractOneParent
import com.intellij.workspaceModel.storage.impl.updateOneToAbstractOneParentOfChild
import org.jetbrains.deft.ObjBuilder
import org.jetbrains.deft.Type
import org.jetbrains.deft.annotations.Abstract
import org.jetbrains.deft.annotations.Child

@GeneratedCodeApiVersion(1)
@GeneratedCodeImplVersion(1)
open class ChildSingleFirstEntityImpl : ChildSingleFirstEntity, WorkspaceEntityBase() {

  companion object {
    internal val PARENTENTITY_CONNECTION_ID: ConnectionId = ConnectionId.create(ParentSingleAbEntity::class.java,
                                                                                ChildSingleAbstractBaseEntity::class.java,
                                                                                ConnectionId.ConnectionType.ABSTRACT_ONE_TO_ONE, false)

    val connections = listOf<ConnectionId>(
      PARENTENTITY_CONNECTION_ID,
    )

  }

  @JvmField
  var _commonData: String? = null
  override val commonData: String
    get() = _commonData!!

  override val parentEntity: ParentSingleAbEntity
    get() = snapshot.extractOneToAbstractOneParent(PARENTENTITY_CONNECTION_ID, this)!!

  @JvmField
  var _firstData: String? = null
  override val firstData: String
    get() = _firstData!!

  override fun connectionIdList(): List<ConnectionId> {
    return connections
  }

  class Builder(val result: ChildSingleFirstEntityData?) : ModifiableWorkspaceEntityBase<ChildSingleFirstEntity>(), ChildSingleFirstEntity.Builder {
    constructor() : this(ChildSingleFirstEntityData())

    override fun applyToBuilder(builder: MutableEntityStorage) {
      if (this.diff != null) {
        if (existsInBuilder(builder)) {
          this.diff = builder
          return
        }
        else {
          error("Entity ChildSingleFirstEntity is already created in a different builder")
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
      if (!getEntityData().isCommonDataInitialized()) {
        error("Field ChildSingleAbstractBaseEntity#commonData should be initialized")
      }
      if (_diff != null) {
        if (_diff.extractOneToAbstractOneParent<WorkspaceEntityBase>(PARENTENTITY_CONNECTION_ID, this) == null) {
          error("Field ChildSingleAbstractBaseEntity#parentEntity should be initialized")
        }
      }
      else {
        if (this.entityLinks[EntityLink(false, PARENTENTITY_CONNECTION_ID)] == null) {
          error("Field ChildSingleAbstractBaseEntity#parentEntity should be initialized")
        }
      }
      if (!getEntityData().isFirstDataInitialized()) {
        error("Field ChildSingleFirstEntity#firstData should be initialized")
      }
      if (!getEntityData().isEntitySourceInitialized()) {
        error("Field ChildSingleFirstEntity#entitySource should be initialized")
      }
    }

    override fun connectionIdList(): List<ConnectionId> {
      return connections
    }

    // Relabeling code, move information from dataSource to this builder
    override fun relabel(dataSource: WorkspaceEntity, parents: Set<WorkspaceEntity>?) {
      dataSource as ChildSingleFirstEntity
      this.commonData = dataSource.commonData
      this.firstData = dataSource.firstData
      this.entitySource = dataSource.entitySource
      if (parents != null) {
        this.parentEntity = parents.filterIsInstance<ParentSingleAbEntity>().single()
      }
    }


    override var commonData: String
      get() = getEntityData().commonData
      set(value) {
        checkModificationAllowed()
        getEntityData().commonData = value
        changedProperty.add("commonData")
      }

    override var parentEntity: ParentSingleAbEntity
      get() {
        val _diff = diff
        return if (_diff != null) {
          _diff.extractOneToAbstractOneParent(PARENTENTITY_CONNECTION_ID, this) ?: this.entityLinks[EntityLink(false,
                                                                                                               PARENTENTITY_CONNECTION_ID)]!! as ParentSingleAbEntity
        }
        else {
          this.entityLinks[EntityLink(false, PARENTENTITY_CONNECTION_ID)]!! as ParentSingleAbEntity
        }
      }
      set(value) {
        checkModificationAllowed()
        val _diff = diff
        if (_diff != null && value is ModifiableWorkspaceEntityBase<*> && value.diff == null) {
          if (value is ModifiableWorkspaceEntityBase<*>) {
            value.entityLinks[EntityLink(true, PARENTENTITY_CONNECTION_ID)] = this
          }
          // else you're attaching a new entity to an existing entity that is not modifiable
          _diff.addEntity(value)
        }
        if (_diff != null && (value !is ModifiableWorkspaceEntityBase<*> || value.diff != null)) {
          _diff.updateOneToAbstractOneParentOfChild(PARENTENTITY_CONNECTION_ID, this, value)
        }
        else {
          if (value is ModifiableWorkspaceEntityBase<*>) {
            value.entityLinks[EntityLink(true, PARENTENTITY_CONNECTION_ID)] = this
          }
          // else you're attaching a new entity to an existing entity that is not modifiable

          this.entityLinks[EntityLink(false, PARENTENTITY_CONNECTION_ID)] = value
        }
        changedProperty.add("parentEntity")
      }

    override var firstData: String
      get() = getEntityData().firstData
      set(value) {
        checkModificationAllowed()
        getEntityData().firstData = value
        changedProperty.add("firstData")
      }

    override var entitySource: EntitySource
      get() = getEntityData().entitySource
      set(value) {
        checkModificationAllowed()
        getEntityData().entitySource = value
        changedProperty.add("entitySource")

      }

    override fun getEntityData(): ChildSingleFirstEntityData = result ?: super.getEntityData() as ChildSingleFirstEntityData
    override fun getEntityClass(): Class<ChildSingleFirstEntity> = ChildSingleFirstEntity::class.java
  }
}

class ChildSingleFirstEntityData : WorkspaceEntityData<ChildSingleFirstEntity>() {
  lateinit var commonData: String
  lateinit var firstData: String

  fun isCommonDataInitialized(): Boolean = ::commonData.isInitialized
  fun isFirstDataInitialized(): Boolean = ::firstData.isInitialized

  override fun wrapAsModifiable(diff: MutableEntityStorage): ModifiableWorkspaceEntity<ChildSingleFirstEntity> {
    val modifiable = ChildSingleFirstEntityImpl.Builder(null)
    modifiable.allowModifications {
      modifiable.diff = diff
      modifiable.snapshot = diff
      modifiable.id = createEntityId()
      modifiable.entitySource = this.entitySource
    }
    modifiable.changedProperty.clear()
    return modifiable
  }

  override fun createEntity(snapshot: EntityStorage): ChildSingleFirstEntity {
    val entity = ChildSingleFirstEntityImpl()
    entity._commonData = commonData
    entity._firstData = firstData
    entity.entitySource = entitySource
    entity.snapshot = snapshot
    entity.id = createEntityId()
    return entity
  }

  override fun getEntityInterface(): Class<out WorkspaceEntity> {
    return ChildSingleFirstEntity::class.java
  }

  override fun serialize(ser: EntityInformation.Serializer) {
  }

  override fun deserialize(de: EntityInformation.Deserializer) {
  }

  override fun createDetachedEntity(parents: List<WorkspaceEntity>): WorkspaceEntity {
    return ChildSingleFirstEntity(commonData, firstData, entitySource) {
      this.parentEntity = parents.filterIsInstance<ParentSingleAbEntity>().single()
    }
  }

  override fun getRequiredParents(): List<Class<out WorkspaceEntity>> {
    val res = mutableListOf<Class<out WorkspaceEntity>>()
    res.add(ParentSingleAbEntity::class.java)
    return res
  }

  override fun equals(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as ChildSingleFirstEntityData

    if (this.commonData != other.commonData) return false
    if (this.firstData != other.firstData) return false
    if (this.entitySource != other.entitySource) return false
    return true
  }

  override fun equalsIgnoringEntitySource(other: Any?): Boolean {
    if (other == null) return false
    if (this::class != other::class) return false

    other as ChildSingleFirstEntityData

    if (this.commonData != other.commonData) return false
    if (this.firstData != other.firstData) return false
    return true
  }

  override fun hashCode(): Int {
    var result = entitySource.hashCode()
    result = 31 * result + commonData.hashCode()
    result = 31 * result + firstData.hashCode()
    return result
  }

  override fun hashCodeIgnoringEntitySource(): Int {
    var result = javaClass.hashCode()
    result = 31 * result + commonData.hashCode()
    result = 31 * result + firstData.hashCode()
    return result
  }
}
